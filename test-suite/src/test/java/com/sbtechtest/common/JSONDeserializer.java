package com.sbtechtest.common;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDeserializer {
	// As of now this has only objectMapper , it can be added with additional functions to deserialize the Json response
	public static JSONDeserializer  instance = new JSONDeserializer ();
	ObjectMapper myObjMap = new ObjectMapper();


	private JSONDeserializer(){}

	//Get the only object available
	public static JSONDeserializer getInstance(){
		return instance;
	}
	public JsonNode getJsonNode(String jsonRsp) throws IOException, JsonParseException,
	JsonMappingException {
		JsonNode node = myObjMap.readValue(jsonRsp, JsonNode.class);
		return node;
	}


}
