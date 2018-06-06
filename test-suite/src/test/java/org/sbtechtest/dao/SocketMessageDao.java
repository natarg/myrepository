package org.sbtechtest.dao;

import org.json.simple.JSONObject;



public interface SocketMessageDao {
	// using composite design pattern to construct message body or request body as applicable for a websocket message or REST request body message
	// The below interface has been created only for the websocket message given in the tech test.
	//  this has getter and setter


	public void setEntityStatus(String entity, int entityNo);
	public JSONObject getEntityStatus();

	public JSONObject getSubscribeObj();
	public void setSubscribeObj( String id);
	public void setUnSubscribeObj(String id);
	public void setUnSubscribeObj();
	public JSONObject getUnSubscribeObj();



}
