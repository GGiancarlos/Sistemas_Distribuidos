package com.srpc.communication;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

	public static RequestPacket string2RequestPacket(String data) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		return om.readValue(data, RequestPacket.class);		
	}
	
	public static ResponsePacket string2ResponsePacket(String data) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		return om.readValue(data, ResponsePacket.class);
	}
	
	public static String requestPacket2String(RequestPacket rp) 
			throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(rp);
	}
	
	public static String responsePacket2String(ResponsePacket rp) 
			throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(rp);
	}
	
	public static String object2String(Object obj) 
			throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(obj);
	}

}
