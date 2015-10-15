package com.srpc.transport;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.srpc.service.ServiceManager;

public class RpcServer {
	private Server server;

	public RpcServer(int port, ServiceManager service) {
		server = new Server(port);		
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handler.setContextPath("/");
		handler.addServlet(new ServletHolder(new ServerHttpServlet(service)), "/simpleRPC");        
        server.setHandler(handler);        
	}
	
	public void start() throws Exception {
		server.start();
		server.join();
	}

}
