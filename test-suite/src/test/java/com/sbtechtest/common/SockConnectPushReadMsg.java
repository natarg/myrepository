package com.sbtechtest.common;

import java.net.URI;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.sbtechtest.cpo.SocketMessages;
import com.sbtechtest.cpo.SocketMessaging;

public class SockConnectPushReadMsg {
	private static SockConnectPushReadMsg instance = new SockConnectPushReadMsg();
	SocketMessages msgObj = new SocketMessaging();

	GetUrl uriObj = GetUrl.getInstance();
	private SockConnectPushReadMsg(){}

	//Get the only object available
	public static SockConnectPushReadMsg getInstance(){
		return instance;
	}

	public void connect(final String msg) throws Exception {
		final WebSocketClient client = new WebSocketClient();

		client.start();

		final WebSocketAdapter socket = new WebSocketAdapter() {
			@Override
			public void onWebSocketConnect(Session session) {
				session.getRemote().sendStringByFuture((msgObj.subscribe(msg)).toJSONString());

				session.close();
			}
		};

		client.connect(
				socket,
				URI.create(uriObj.readUrlFile("socketUri")),
				new ClientUpgradeRequest()
				);

		Thread.sleep(1000L);

		client.stop();
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("Got msg: %s%n", msg);
	}
}
