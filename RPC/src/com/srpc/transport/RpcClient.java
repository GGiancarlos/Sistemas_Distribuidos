package com.srpc.transport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.InputStreamContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.srpc.communication.Mapper;
import com.srpc.communication.RequestPacket;
import com.srpc.communication.ResponsePacket;
import com.srpc.service.ServiceProxy;

public class RpcClient {
	
	private HttpClient client;
	private String address;
	
	public RpcClient(String address) {
		this.address = address;
		
		client = new HttpClient();
		client.setFollowRedirects(false);
		try {
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResponsePacket sendRequest(RequestPacket rp) 
			throws IOException {
		/*String content = "{\"serviceName\":\"CalculatorService\", \"methodName\":\"sum2Num\","
				+ "\"params\":[{\"type\":\"int\", \"value\":\"20\"},{\"type\":\"int\", \"value\":\"10\"}]}";*/
		
		String content = Mapper.requestPacket2String(rp);
		
		InputStream input = new ByteArrayInputStream(content.getBytes());
		
		Request request = client.newRequest(address);
		request.method(HttpMethod.POST);
		request.content(new InputStreamContentProvider(input), "application/json");
		request.timeout(Long.MAX_VALUE, TimeUnit.SECONDS);
		try {
			ContentResponse response = request.send();
			String res = response.getContentAsString();
			return Mapper.string2ResponsePacket(res);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public Object getProxy(Class<?> inter, String servicio) {
		return ServiceProxy.getServicio(inter, servicio, this);
	}
	
	public void stop() {
		try {
			client.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
