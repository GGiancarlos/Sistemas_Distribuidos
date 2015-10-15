package com.srpc.test;

import com.srpc.transport.RpcClient;
import java.util.Scanner;

public class TestCalc {

	public static void main(String[] args) {
		RpcClient client = new RpcClient("http://localhost:70/simpleRPC");		
	Calculator cal = (Calculator)client.getProxy(Calculator.class, "CalculatorService");
	int value = 1;
        int op1;
        int op2;
        Scanner scan = new Scanner(System.in);
        while(value == 1){
            System.out.println("Suma de Numeros Enteros:");
            System.out.print("Ingrese el Primer Operando: ");
            op1 = scan.nextInt();
            System.out.print("Ingrese el Segundo Operando: ");
            op2 = scan.nextInt();
            value = cal.sum2Num(op1, op2);
            System.out.println("El resultado es: "+value);
            System.out.println("Desea COntinuar 1:SI 0:NO ");
            do{
                value = scan.nextInt();
            }
            while(!(value == 1 || value == 0));
        }
            client.stop();
    }	
}
