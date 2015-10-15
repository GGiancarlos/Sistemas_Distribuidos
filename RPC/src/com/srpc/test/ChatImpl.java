package com.srpc.test;

import com.srpc.service.Service;
import java.util.Scanner;

public class ChatImpl  extends Service implements Chat {

	public ChatImpl( ) {
		super("ChatService");
        }

	@Override
        public String hablar(String nombre, String msj) {
            System.out.println(nombre + ": " + msj);
            Scanner scan = new Scanner(System.in);
            String texto = scan.nextLine();
            return texto;
	}
}
