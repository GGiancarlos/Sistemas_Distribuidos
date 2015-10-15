package com.srpc.communication;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponsePacketManager {
	
	private HttpServletResponse response;

	public ResponsePacketManager(HttpServletResponse response) {
		this.response = response;
	}
	
	public void write() throws IOException, InterruptedException {
		response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("{\"error\":\"hola\"}");
        synchronized (this) {
            try {
                this.wait(5000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        response.getWriter().println("{\"error\":\"otro\"}");
    }

}
