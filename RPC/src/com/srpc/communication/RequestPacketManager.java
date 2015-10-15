package com.srpc.communication;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.srpc.service.ServiceManager;

public class RequestPacketManager {
	
	private RequestPacket request;
	private ServiceManager service;

	public RequestPacketManager(RequestPacket request, ServiceManager service) {
		this.request = request;
		this.service = service;
	}
	
	public ResponsePacket processRequest() 
			throws NoSuchMethodException, SecurityException, 
			InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException, JsonProcessingException {
		List<String> argsType = new ArrayList<String>();
		List<Object> args = new ArrayList<Object>();
		
		for (ParamPacket p : request.params) {
			argsType.add(p.type);
			args.add(castObject(p.type, p.value));
		}
		
		Object result = service.executeMethod(request.serviceName, request.methodName, argsType, args);
		ResponsePacket rp = new ResponsePacket();
		rp.response = Mapper.object2String(result);
		return rp;
	}
	
	private Object castObject(String type, String arg) {
		switch (type) {
		case "int":
			return (int)Integer.parseInt(arg);
			
		case "Integer":
			return Integer.getInteger(arg);
			
		case "String":
			return arg;
			
		case "float":
			return (float)Float.parseFloat(arg);
			
		case "Float":
			return Float.parseFloat(arg);
			
		case "boolean":
			return (boolean)Boolean.parseBoolean(arg);
			
		case "Boolean":
			return Boolean.parseBoolean(arg);
			
		default:
			return null;
		}
	}

}
