package com.sbtechtest.cpo;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class SocketMessages implements RequestBodyMessages{

	private String entity;
	private int entityNo;

	private final JSONObject subscribe = new JSONObject();
	private final JSONObject unsubscribe = new JSONObject();


	public void setStatus(String entity, int entityNo){
		this.entity = entity;
		this.entityNo = entityNo;
	}
	public JSONObject getStatus() {
		JSONObject statObj = new JSONObject();
		statObj.put("type", this.entity);
		statObj.put("id", this.entityNo);
		return statObj;
	}


	public JSONObject getSubscribe(){
		//	System.out.println("printing n getter setter"+ this.subscribe.toJSONString());
		return this.subscribe;
	}




	public void setSubscribe( String id) {

		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		this.subscribe.put("type","subscribe");
		this.subscribe.put("keys", arr1);


	}

	public void setUnSubscribe(String id) {

		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		this.unsubscribe.put("type", "unsubscribe");
		this.unsubscribe.put("keys", id);

	}


	public void setUnSubscribe() {


		this.unsubscribe.put("type", "unsubscribe");


	}
	public JSONObject getUnSubscribe() {


		return this.unsubscribe;


	}
}
