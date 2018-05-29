package com.sbtechtest.api;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.sbtechtest.common.GetUrl;
import com.sbtechtest.cpo.SocketMessages;
import com.sbtechtest.cpo.SocketMessaging;



@WebSocket(maxTextMessageSize = 64 * 1024)
public class SocketMsgsEcho {
	GetUrl uriObj = GetUrl.getInstance();
	private final CountDownLatch closeLatch;
	SocketMessages msgObj = new SocketMessaging();
	Future<Void> fut;
	@SuppressWarnings("unused")
	public Session session;

	public SocketMsgsEcho() {
		this.closeLatch = new CountDownLatch(1);
	}

	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.printf("Connection closed: %d - %s%n", statusCode, reason);
		this.session = null;
		this.closeLatch.countDown();
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.printf("Got connect: %s%n", session);
		this.session = session;
		try {


			fut = session.getRemote().sendStringByFuture(msgObj.getStatus("type", 21249934).toJSONString());

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("Got msg: %s%n", msg);
	}





}
