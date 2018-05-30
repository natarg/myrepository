package com.sbtechtest.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class SocketReader {

	GetUrl uriObj = GetUrl.getInstance();

	public void openSocReadMsg() throws Exception {
		WebSocketClient client = new WebSocketClient();
		try {
			SocketConnector socket = openSocket(client, new SocketConnector());

			System.out.println("Printing messages on the socket"+socket.getMessages(0));
		}
		finally {
			client.stop();
		}
	}


	private <T> T openSocket(WebSocketClient client, T socket) throws Exception,
	URISyntaxException, InterruptedException, ExecutionException, IOException {
		client.start();
		ClientUpgradeRequest request = new ClientUpgradeRequest();
		URI uri = new URI(uriObj.readUrlFile("socketUri"));
		Session session = client.connect(socket, uri, request).get();
		//session.getRemote().sendString(HANDSHAKE);
		Thread.sleep(200);
		return socket;
	}


}
