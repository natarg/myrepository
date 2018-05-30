package com.sbtechtest.api;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.common.SockConnectPushReadMsg;
import com.sbtechtest.cpo.SocketMessages;
import com.sbtechtest.cpo.SocketMessaging;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestWebSocketMessages {
	GetUrl uriObj = GetUrl.getInstance();
	SockConnectPushReadMsg sockObj = SockConnectPushReadMsg.getInstance();

	SocketMessages socMsgObj = new SocketMessaging();


	@Given("^a socket \"([^\"]*)\" is sent to get status on an event$")
	public void connectSockMsg1(String msg) throws Exception {

		sockObj.connect(msg);


	}

	@Then("^the socket message is published with status on the event$")
	public void getMsgBody(){

		// to open socket and assert the message content containing the specific events



	}




	@Then("^the event's status is published on the socket$")
	public void the_event_s_status_is_published_on_the_socket() throws Throwable {



	}






}
