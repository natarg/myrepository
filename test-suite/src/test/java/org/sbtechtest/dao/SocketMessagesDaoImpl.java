package org.sbtechtest.dao;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class SocketMessagesDaoImpl implements SocketMessageDao{

	private String entity;
	private int entityNo;

	private final JSONObject subscribe = new JSONObject();
	private final JSONObject unsubscribe = new JSONObject();


	public void setEntityStatus(String entity, int entityNo) {

		this.entity = entity;
		this.entityNo = entityNo;

	}
	public JSONObject getEntityStatus() {
		JSONObject statObj = new JSONObject();
		statObj.put("type", this.entity);
		statObj.put("id", this.entityNo);
		return statObj;
	}
	public JSONObject getSubscribeObj() {
		// TODO Auto-generated method stub
		return this.subscribe;
	}
	public void setSubscribeObj(String id) {
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		this.subscribe.put("type","subscribe");
		this.subscribe.put("keys", arr1);

	}
	public void setUnSubscribeObj(String id) {
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		this.unsubscribe.put("type", "unsubscribe");
		this.unsubscribe.put("keys", id);

	}
	public void setUnSubscribeObj() {
		this.unsubscribe.put("type", "unsubscribe");

	}
	public JSONObject getUnSubscribeObj() {
		return this.unsubscribe;
	}
}
