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

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;
@WebSocket()
public class TestSocketMessages  {
	List<String> messages = new ArrayList<String>();


	private final CountDownLatch closeLatch;
	GetUrl uriObj = GetUrl.getInstance();

	private Scenario scenario;
	public  String message;
	@SuppressWarnings("unused")
	private Session session;

	public TestSocketMessages(String message) {

		this.message = message;
		this.closeLatch = new CountDownLatch(1);


	}





	@Then("the response message is verified not to be null$")
	public void verifytheresponseoutcomes() throws Throwable {



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
