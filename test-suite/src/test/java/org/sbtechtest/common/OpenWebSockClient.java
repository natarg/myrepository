package org.sbtechtest.common;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.sbtechtest.api.SocketMessagesStepDef;
import org.sbtechtest.dao.SocketMessageDao;
import org.sbtechtest.dao.SocketMessagesDaoImpl;

public class OpenWebSockClient {
	GetUrl uriObj = GetUrl.getInstance();
	private final  SocketMessageDao socMsgObj = new SocketMessagesDaoImpl();
	public List<String> messages = new ArrayList<String>();
	public String message;

	public void getConnection(String message) {
		this.message = message;
		WebSocketClient client = new WebSocketClient();
		SocketMessagesStepDef sockObj = new SocketMessagesStepDef(this);
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
