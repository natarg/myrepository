package com.sbtechtest.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.websocket.api.WebSocketAdapter;



public class SocketReader extends WebSocketAdapter {

	private final List<String> messages = new ArrayList<String>();

	private Integer closeStatus;

	@Override
	public void onWebSocketText(String message) {
		this.messages.add(message);
	}

	public String getMessages(int index) {
		return this.messages.get(index);
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		this.closeStatus = statusCode;
	}

	public Integer getCloseStatus() {
		return this.closeStatus;
	}

}


