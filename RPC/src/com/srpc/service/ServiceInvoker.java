package com.srpc.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.srpc.communication.ParamPacket;
import com.srpc.communication.RequestPacket;
import com.srpc.communication.ResponsePacket;
import com.srpc.transport.RpcClient;

public class ServiceInvoker implements InvocationHandler{
	
	private String serviceName;
	private RpcClient client;
	
	public ServiceInvoker(String serviceName, RpcClient client) {
		this.serviceName = serviceName;
		this.client = client;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		RequestPacket request = createRequest(method, args);
		ResponsePacket rp = client.sendRequest(request);
		return castResult(method.getReturnType(), rp.response);
	}
	
	private Object castResult(Class<?> type, String value) {
		String ty = type.getSimpleName();
		if(ty.equals("int")) {
			return (int)Integer.parseInt(value);
		} else if(ty.equals("Integer")){
			return Integer.parseInt(value);
		} else if(ty.equals("String")){
			return value;
		} else if(ty.equals("float")){
			return (float)Float.parseFloat(value);
		} else if(ty.equals("Float")){
			return Float.parseFloat(value);
		} else if(ty.equals("Boolean")){
			return Boolean.parseBoolean(value);
		} else if(ty.equals("boolean")){
			return (boolean)Boolean.parseBoolean(value);
		} else {
			return null;
		}
	}
	
	private RequestPacket createRequest(Method method, Object[] args) {
		RequestPacket request = new RequestPacket();
		request.methodName = method.getName();
		request.serviceName = serviceName;
		
		Class<?>[] argType = method.getParameterTypes();		
		List<ParamPacket> params = new ArrayList<ParamPacket>();
		
		for (int i = 0; i < args.length; i++) {
			ParamPacket p = new ParamPacket();
			p.type = argType[i].getSimpleName();
			p.value = args[i].toString();
			params.add(p);
		}
		
		request.params = params;
		return request;
	}
	
}
