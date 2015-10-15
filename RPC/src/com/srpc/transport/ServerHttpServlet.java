package com.srpc.transport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srpc.communication.StreamManager;
import com.srpc.exception.ExceptionManager;
import com.srpc.service.ServiceManager;

@SuppressWarnings("serial")
public class ServerHttpServlet extends HttpServlet {

	private ServiceManager service;
	
	public ServerHttpServlet(ServiceManager service) {
		this.service = service;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		StreamManager sm = new StreamManager(service);
		sm.init(request, response);
		System.out.println("Post");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		ExceptionManager.writeErrorNotAllowed(response);
	}

}
