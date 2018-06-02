package com.sbtechtest.api;

import org.picocontainer.PicoCompositionException;

import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.java.en.When;

public class GivenSocMsg {
	private final  RequestBodyMessages socMsgObj = new SocketMessages();
	OpenWebSockClient clientObj = new OpenWebSockClient();
	public String message;

	@When("^the socket client is opened with \"([^\"]*)\" ready to be pushed to subscribe for all outcomes$")
	public void the_message_is_pushed_via_websocket_api(String arg1) throws PicoCompositionException {
		socMsgObj.setSubscribe(arg1);
		clientObj.getConnection(socMsgObj.getSubscribe().toJSONString());
	}

	@When("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(int no) throws PicoCompositionException {

		socMsgObj.setStatus("event", no);



		clientObj.getConnection(socMsgObj.getStatus().toJSONString());

	}
}
