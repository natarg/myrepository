package com.sbtechtest.cpo;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class SocketMessages implements RequestBodyMessages{



	public JSONObject getStatus(String entity, int entityNo) {
		JSONObject statObj = new JSONObject();
		statObj.put("type", "event");
		statObj.put("id", entityNo);
		return statObj;
	}

	public JSONObject subscribe( String id) {
		JSONObject subscribe = new JSONObject();
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		subscribe.put("type","subscribe");
		subscribe.put("keys", arr1);
		return subscribe;

	}

	public JSONObject unsubscribe(String id) {
		JSONObject unsubscribe = new JSONObject();
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add(id);
		unsubscribe.put("type", "unsubscribe");
		unsubscribe.put("keys", id);
		return unsubscribe;
	}

	public JSONObject unsubscribe() {
		JSONObject unsubscribe = new JSONObject();

		unsubscribe.put("type", "subscribe");

		return unsubscribe;
	}

}
