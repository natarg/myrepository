package com.sbtechtest.api;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

import cucumber.api.java.en.When;

public class OpenWebSockClient {
	GetUrl uriObj = GetUrl.getInstance();
	private final  RequestBodyMessages socMsgObj = new SocketMessages();




	@When("^the socket client is opened with \"([^\"]*)\" ready to be pushed to subscribe for all outcomes$")
	public void the_message_is_pushed_via_websocket_api(String arg1) throws Throwable {
		socMsgObj.setSubscribe(arg1);

		TestSocketMessages sockObj = new TestSocketMessages(socMsgObj.getSubscribe().toJSONString());
		getConnection(sockObj);
	}

	private void getConnection(TestSocketMessages sockObj) {
		WebSocketClient client = new WebSocketClient();
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

	@When("^there is a websocket \"([^\"]*)\" to subscribe to a specific event$")
	public void there_is_a_websocket_to_subscribe_to_a_specific_event(int no) throws Throwable {

		socMsgObj.setStatus("event", no);;
		TestSocketMessages sockObj = new TestSocketMessages(socMsgObj.getStatus().toJSONString());
		getConnection(sockObj);
	}

}
