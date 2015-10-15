package com.srpc.test;

import com.srpc.transport.RpcClient;
import java.util.Scanner;

public class TestChat {

	public static void main(String[] args) {
		RpcClient client = new RpcClient("http://localhost:70/simpleRPC");		
                Chat msj = (Chat)client.getProxy(Chat.class, "ChatService");
                
                Scanner scan = new Scanner(System.in);
                System.out.println("Ingrese su nombre:");
                String nombre = scan.nextLine();
                for(int i = 0; i< 3; i++){
                    String texto = scan.nextLine();
                    String result = msj.hablar(nombre,texto);
                    System.out.println("Server:" +result);
                }
		client.stop();
	}
}
