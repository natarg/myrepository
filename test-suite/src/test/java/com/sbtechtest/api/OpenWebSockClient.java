package com.sbtechtest.api;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.sbtechtest.common.GetUrl;

import cucumber.api.java.en.When;

public class OpenWebSockClient {
	GetUrl uriObj = GetUrl.getInstance();





	@When("^the socket client is opened$")
	public void the_message_is_pushed_via_websocket_api() throws Throwable {
		WebSocketClient client = new WebSocketClient();
		TestSocketMessages sockObj = new TestSocketMessages();

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

}
