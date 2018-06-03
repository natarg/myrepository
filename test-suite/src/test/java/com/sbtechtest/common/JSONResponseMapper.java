package com.sbtechtest.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResponseMapper {
	// As of now this has only objectMapper , it can be added with additional functions to get to the elements in the Json response
	public static JSONResponseMapper  instance = new JSONResponseMapper ();
	ObjectMapper myObjMap = new ObjectMapper();


	private JSONResponseMapper(){}

	//Get the only object available
	public static JSONResponseMapper getInstance(){
		return instance;
	}
	public JsonNode getJsonNode(String jsonRsp) throws IOException, JsonParseException,
	JsonMappingException {
		JsonNode node = myObjMap.readValue(jsonRsp, JsonNode.class);
		return node;
	}
	public JsonNode readJson(String jsonRsp) throws IOException, JsonParseException,
	JsonMappingException {
		JsonNode node = myObjMap.readTree(jsonRsp);
		return node;
	}


}
