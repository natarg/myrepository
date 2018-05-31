package com.sbtechtest.common;

import java.net.URI;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.json.simple.JSONObject;

import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;

public class SockConnectPushMsg {
	private static SockConnectPushMsg instance = new SockConnectPushMsg();
	RequestBodyMessages msgObj = new SocketMessages();

	GetUrl uriObj = GetUrl.getInstance();
	private SockConnectPushMsg(){}

	//Get the only object available
	public static SockConnectPushMsg getInstance(){
		return instance;
	}

	public void connect(final JSONObject msg) throws Exception {
		final WebSocketClient client = new WebSocketClient();

		client.start();

		final WebSocketAdapter socket = new WebSocketAdapter() {
			@Override
			public void onWebSocketConnect(Session session) {
				session.getRemote().sendStringByFuture(msg.toJSONString());

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
