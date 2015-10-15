package com.srpc.service;

import java.lang.reflect.Proxy;

import com.srpc.test.Calculator;
import com.srpc.transport.RpcClient;

public class ServiceProxy {
	
	public static Object getServicio(Class<?> inter, String servicio, RpcClient client) {
		return newInstance(inter, servicio, client);
	}
	
	private static Object newInstance(Class<?> inter, String servicio, RpcClient client){
		return Proxy.newProxyInstance(inter.getClassLoader(), new Class[]{inter}, new ServiceInvoker(servicio, client));
	}

}
