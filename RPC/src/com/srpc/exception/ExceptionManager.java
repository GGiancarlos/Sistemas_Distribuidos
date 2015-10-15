package com.srpc.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionManager {

	private static void write(HttpServletResponse response, String error) {		
		try {
			PrintWriter writer;
			writer = response.getWriter();
			response.setContentType("application/json");
			ExceptionPacket packet = new ExceptionPacket();
			packet.error = error;
			ObjectMapper om = new ObjectMapper();			
			writer.println(om.writeValueAsString(packet));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void writeError(HttpServletResponse response, Exception e) {
		if(e instanceof NoSuchMethodException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			write(response, "Metodo no encontrado");
			return;
		} else if(e instanceof SecurityException) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			write(response, "Problema de suguridad al acceder al metodo");
			return;
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			write(response, "Se produjo un error al procesar su pedido");
			return;
		}
	}
	
	public static void writeErrorNotAllowed(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		write(response, "Request no permitido");
		return;		
	}

}
