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
import org.json.simple.JSONObject;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
@WebSocket()
public class TestSocketMessages  {
	List<String> messages = new ArrayList<String>();
	private final  RequestBodyMessages socMsgObj = new SocketMessages();

	private final CountDownLatch closeLatch;
	GetUrl uriObj = GetUrl.getInstance();

	private Scenario scenario;
	public  String message;
	@SuppressWarnings("unused")
	private Session session;

	public TestSocketMessages() {


		this.closeLatch = new CountDownLatch(1);


	}

	@Before("@run_websocket_subscribealloutcomes,@run_websocket_subscribespecificevent")
	private void setScenarioObj(Scenario scenario){
		// This writes on to the cucumber html reports produced, so the report can print what needs to be checked.
		this.scenario = scenario;

	}
	@Given("^there is a message to subscribetoall outcomes$")
	public void subscribeallevents() throws Throwable {

		socMsgObj.setSubscribe("m.*");

		this.message = socMsgObj.getSubscribe().toJSONString();





	}
	@When("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(String arg1) throws Throwable {
		socMsgObj.setSubscribe(arg1);

	}

	@Then("the response is verified for relevant outcomes when the message is pushed$")
	public void verifytheresponseoutcomes() throws Throwable {
		System.out.println("Atleast i will print it here"+ messages);

	}
	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}
	public void connect(final JSONObject msg) throws Exception {

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

			fut = session.getRemote().sendStringByFuture(message);
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

		messages.add(message);



	}


}
