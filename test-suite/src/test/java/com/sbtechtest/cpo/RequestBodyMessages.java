package com.sbtechtest.cpo;

import org.json.simple.JSONObject;



public interface RequestBodyMessages {
	// using composite design pattern to construct message body or request body as applicable for a websocket message or REST request body message
	// The below interface has been created only for the websocket message given in the tech test.
	// Also this has getter and setter


	public void setStatus(String entity, int entityNo);
	public JSONObject getStatus();

	public JSONObject getSubscribe();
	public void setSubscribe( String id);
	public void setUnSubscribe(String id);
	public void setUnSubscribe();
	public JSONObject getUnSubscribe();



}