package com.srpc.application;

import com.srpc.service.Service;
import com.srpc.service.ServiceManager;
import com.srpc.transport.RpcServer;

public class RpcApp {
	
	private int port;
	private ServiceManager service;
	
	public RpcApp(int port) {
		this.port = port;
		this.service = new ServiceManager();
	}
	
	public void addService(Class<? extends Service> servicioImpl) {
		Service newObj;
		try {
			newObj = servicioImpl.newInstance();
			service.addService(newObj.getName(), servicioImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void start() {
		RpcServer rpc = new RpcServer(port, service);
		try {
			rpc.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
