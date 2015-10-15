package com.srpc.test;

import com.srpc.application.RpcApp;
import com.srpc.exception.DuplicateServiceException;
import com.srpc.service.ServiceManager;
import com.srpc.transport.RpcServer;

public class TestServer {

	public static void main(String[] args) {
		RpcApp rpc = new RpcApp(70);
		rpc.addService(CalculatorImpl.class);
                rpc.addService(ChatImpl.class);
		rpc.start();	
	}

}
