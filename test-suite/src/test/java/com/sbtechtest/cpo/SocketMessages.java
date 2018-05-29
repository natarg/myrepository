package com.sbtechtest.cpo;

import org.json.simple.JSONObject;



public interface SocketMessages {
	// using composite design pattern to construct message body or request body as applicable for a websocket message or REST request body message
	// The below interface has been created only for the websocket message given in the tech test.

	public JSONObject getStatus(String entity, int entityNo);
	public JSONObject subscribe(String id);
	public JSONObject unsubscribe();
	public JSONObject unsubscribe(String id);



}
