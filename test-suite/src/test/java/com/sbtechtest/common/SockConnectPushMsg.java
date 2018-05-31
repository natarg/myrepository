package com.sbtechtest.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.simple.JSONObject;

import com.sbtechtest.cpo.RequestBodyMessages;
import com.sbtechtest.cpo.SocketMessages;
@WebSocket(maxTextMessageSize = 64 * 1024)
public class SockConnectPushMsg  {

	RequestBodyMessages mymsg = new SocketMessages();
	private final CountDownLatch closeLatch;
	GetUrl uriObj = GetUrl.getInstance();
	JSONObject my1 = new JSONObject();


	@SuppressWarnings("unused")
	private Session session;

	public SockConnectPushMsg() {
		this.closeLatch = new CountDownLatch(1);


	}

	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}
	public void connect(final JSONObject msg) throws Exception {

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
		System.out.println("Printing the message sent inside child class"+ mymsg.getSubscribe().toJSONString());
		try {
			Future<Void> fut;
			fut = session.getRemote().sendStringByFuture(mymsg.getSubscribe().toJSONString());
			fut.get(10, TimeUnit.SECONDS);
			/*fut = session.getRemote().sendStringByFuture(mymsg.subscribe("e.21249950").toJSONString());
			fut.get(5, TimeUnit.SECONDS);*/
			//session.close(StatusCode.NORMAL, "I'm done");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("Got msg: %s%n", msg);
	}


}
