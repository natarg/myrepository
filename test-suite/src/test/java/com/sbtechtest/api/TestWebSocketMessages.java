package com.sbtechtest.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.common.SockConnectPushMsg;
import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestWebSocketMessages {
	GetUrl uriObj = GetUrl.getInstance();
	protected final List<String> messages = new ArrayList<String>();

	protected RequestBodyMessages socMsgObj = new SocketMessages();
	protected String message;
	public TestWebSocketMessages() {
		socMsgObj.setSubscribe("o.*");

		message = socMsgObj.getSubscribe().toJSONString();

	}

	@Given("^there is a websocket message to subscribe to all events$")
	public void there_is_a_websocket_message_to_subscribe_to_all_events() throws Throwable {
		socMsgObj.setSubscribe("e.*");

		message = socMsgObj.getSubscribe().toJSONString();

		System.out.println("Printing"+message);

	}

	@When("^the message is pushed via websocket api$")
	public void the_message_is_pushed_via_websocket_api() throws Throwable {
		WebSocketClient client = new WebSocketClient();
		SockConnectPushMsg sockObj = new SockConnectPushMsg();
		System.out.println("Printing the message"+ message);
		try {
			client.start();
			URI echoUri = new URI(uriObj.readUrlFile("socketUri"));
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(sockObj, echoUri, request);
			System.out.printf("Connecting to : %s%n", echoUri);
			sockObj.awaitClose(10, TimeUnit.SECONDS);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				client.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Then("^the response to the message published has status for all events$")
	public void the_response_to_the_message_published_has_status_for_all_events() throws Throwable {



	}

	@Given("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(String arg1) throws Throwable {
		socMsgObj.setSubscribe(arg1);

	}

	@Then("^the response to the message published has status for the specific event requested$")
	public void the_response_to_the_message_published_has_status_for_the_specific_event_requested() throws Throwable {


	}


	@Then("^the event's status is published on the socket$")
	public void the_event_s_status_is_published_on_the_socket() throws Throwable {



	}






}
