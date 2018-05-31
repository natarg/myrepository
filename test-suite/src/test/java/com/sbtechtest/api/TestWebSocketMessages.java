package com.sbtechtest.api;

import org.json.simple.JSONObject;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.common.SockConnectPushMsg;
import com.sbtechtest.common.SocketReader;
import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestWebSocketMessages {
	GetUrl uriObj = GetUrl.getInstance();
	SockConnectPushMsg sockObj = SockConnectPushMsg.getInstance();
	SocketReader readMsg = new SocketReader();
	RequestBodyMessages socMsgObj = new SocketMessages();
	JSONObject message;

	@Given("^there is a websocket message to subscribe to all events$")
	public void there_is_a_websocket_message_to_subscribe_to_all_events() throws Throwable {
		message = socMsgObj.subscribe("e.*");

	}

	@When("^the message is pushed via websocket api$")
	public void the_message_is_pushed_via_websocket_api() throws Throwable {
		sockObj.connect(message);

	}

	@Then("^the response to the message published has status for all events$")
	public void the_response_to_the_message_published_has_status_for_all_events() throws Throwable {


	}

	@Given("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(String arg1) throws Throwable {
		message = socMsgObj.subscribe(arg1);

	}

	@Then("^the response to the message published has status for the specific event requested$")
	public void the_response_to_the_message_published_has_status_for_the_specific_event_requested() throws Throwable {


	}


	@Then("^the event's status is published on the socket$")
	public void the_event_s_status_is_published_on_the_socket() throws Throwable {



	}






}
