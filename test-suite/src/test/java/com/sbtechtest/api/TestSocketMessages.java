package com.sbtechtest.api;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.junit.Assert;
import org.picocontainer.PicoCompositionException;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.common.OpenWebSockClient;
import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
@WebSocket()
public class TestSocketMessages extends OpenWebSockClient   {
	private final  RequestBodyMessages socMsgObj = new SocketMessages();
	private final  OpenWebSockClient clOb;
	private final CountDownLatch closeLatch;
	private final List<String> messages = new ArrayList<String>();
	GetUrl uriObj = GetUrl.getInstance();

	private Scenario scenario;
	@Before("@run_websocket_subscribespecificevent,@run_websocket_subscribealloutcomes")
	public void setScenarioObj(Scenario scenario){
		// This writes on to the cucumber html reports produced, so the report can print what needs to be checked.
		this.scenario = scenario;

	}
	@SuppressWarnings("unused")
	private Session session;
	// The below constructor takes care of dependency injection so that the object reference to OpenWebSockClient and any of its fields's latest values can be inherited here in this test class.
	// This is cucumber pico container's way of DI. The actual Pico container demands component registration if at all Parametrized constructors are used
	public TestSocketMessages(OpenWebSockClient clOb){
		this.clOb = clOb;

		this.closeLatch = new CountDownLatch(1);
	}

	@When("^the socket client is opened with \"([^\"]*)\" ready to be pushed to subscribe for all outcomes$")
	public void the_message_is_pushed_via_websocket_api(String arg1) throws PicoCompositionException {
		socMsgObj.setSubscribe(arg1);
		this.clOb.getConnection(socMsgObj.getSubscribe().toJSONString());
	}

	@When("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(int no) throws PicoCompositionException {

		socMsgObj.setStatus("event", no);

		this.clOb.getConnection(socMsgObj.getStatus().toJSONString());

	}

	@Then("the response message is verified not to be null$")
	public void verifytheresponseoutcomes(){

		Assert.assertNotNull(messages);

	}
	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}


	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
		this.session = null;
		this.closeLatch.countDown();
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.printf("Got connect: %s%n", session);

		this.session = session;

		try {
			Future<Void> fut;

			fut = session.getRemote().sendStringByFuture(clOb.message);
			fut.get(10, TimeUnit.SECONDS);
			/*fut = session.getRemote().sendStringByFuture(mymsg.subscribe("e.21249950").toJSONString());
			fut.get(5, TimeUnit.SECONDS);*/
			//session.close(StatusCode.NORMAL, "I'm done");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("Got msg: %s%n", msg);

		messages.add(clOb.message);


	}


}
