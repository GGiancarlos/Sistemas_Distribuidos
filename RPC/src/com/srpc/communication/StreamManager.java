package com.srpc.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.srpc.exception.ExceptionManager;
import com.srpc.service.ServiceManager;

public class StreamManager {	
	
	private ServiceManager service;
	
	public StreamManager(ServiceManager service) {		
		this.service = service;
	}
	
	private RequestPacket readRequest(HttpServletRequest request) 
			throws JsonParseException, JsonMappingException, IOException {
		BufferedReader reader;
		StringBuffer buffer = new StringBuffer();
		
		String line = null;
		reader = request.getReader();
		
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		
		RequestPacket rp = Mapper.string2RequestPacket(buffer.toString());
		return rp;
	}
	
	private void writeResponse(ResponsePacket responsePacket, HttpServletResponse response) 
			throws IOException {		
		String resp = Mapper.responsePacket2String(responsePacket);
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.println(resp);
		System.out.print(resp);
	}
	
	public void init(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestPacket rp = readRequest(request);
			ResponsePacket res = requestProcess(rp);
			writeResponse(res, response);
		} catch (Exception e){
			ExceptionManager.writeError(response, e);
		}
	}
	
	private ResponsePacket requestProcess(RequestPacket request) 
			throws JsonParseException, JsonMappingException, 
			IOException, NoSuchMethodException, 
			SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		RequestPacketManager requestManager = new RequestPacketManager(request, service);
		ResponsePacket response = requestManager.processRequest();
		return response;		
	}

}
