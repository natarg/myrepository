package com.sbtechtest.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.cpo.SocketMessages;
import com.sbtechtest.cpo.SocketMessaging;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestWebSocketMessages extends SocketMsgsEcho{
	GetUrl uriObj = GetUrl.getInstance();


	SocketMessages socMsgObj = new SocketMessaging();

	@Given("^a socket message is sent to get status on an event$")
	public void connectSock() {
		getConnection();
	}

	@Then("^the socket message is published with status on the event$")
	public void onConnect() throws Exception{

		// to open socket and assert the message content containing the specific events



	}




	@Then("^the event's status is published on the socket$")
	public void the_event_s_status_is_published_on_the_socket() throws Throwable {



	}

	protected void getConnection() {
		WebSocketClient client = new WebSocketClient();
		SocketMsgsEcho socket = new SocketMsgsEcho();
		try {
			client.start();
			URI echoUri = new URI(uriObj.readUrlFile("socketUri"));
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(socket, echoUri, request);
			System.out.printf("Connecting to : %s%n", echoUri);

			socket.awaitClose(5, TimeUnit.SECONDS);
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
	protected <T> T openSocket(WebSocketClient client, T socket) throws Exception,
	URISyntaxException, InterruptedException, ExecutionException, IOException {
		client.start();
		ClientUpgradeRequest request = new ClientUpgradeRequest();
		URI uri = new URI(uriObj.readUrlFile("socketUri"));
		Session session = client.connect(socket, uri, request).get();

		Thread.sleep(200);
		return socket;
	}

}
