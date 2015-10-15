import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class ClientSocket {
	public final static int SOCKET_PORT = 13267;      
	public final static String SERVER = "127.0.0.1";
	public final static String FILE_TO_RECEIVED = "c:/temp/prueba-downloaded.pdf";
	public final static int FILE_SIZE = 1200000;
	
	private static void recibirArchivo(){
		int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    Socket sock = null;
	    
	    System.out.println("Conectando...");
	    try {
			sock = new Socket(SERVER, SOCKET_PORT);		
	    
		    byte[] mybytearray  = new byte[FILE_SIZE];
		    InputStream is;	    
			
		    is = sock.getInputStream();		
			
		    fos = new FileOutputStream(FILE_TO_RECEIVED);
		    bos = new BufferedOutputStream(fos);
		    
		    boolean salir = false;
		    
		    while(!salir){
		    	if(is.available() > 0){
			    	bytesRead = is.read(mybytearray, current, 40960);
			    	current += bytesRead;
		    	}else{
		    		salir = true;
		    	}
		    }
		    /*bytesRead = is.read(mybytearray, 0, mybytearray.length);
		    current = bytesRead;
		    
			do {
				bytesRead =	is.read(mybytearray, current, (mybytearray.length-current));
				if(bytesRead >= 0) current += bytesRead;
			} while(bytesRead > -1);*/
			
			bos.write(mybytearray, 0 , current);
			bos.flush();
			System.out.println("Archivo " + FILE_TO_RECEIVED 
					+ " descargado (" + current + " bytes recibidos)");
	    } catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fos != null) fos.close();
			    if (bos != null) bos.close();
			    if (sock != null) sock.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	    
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		boolean finalizar = false;
		
		while(!finalizar){
			System.out.println("Ingrese el número de opción que desea:");
			System.out.println("1.Recibir archivo");
			System.out.println("2.Salir");
			
			opcion = sc.nextInt();
			
			switch (opcion) {
			case 1:
				recibirArchivo();
				finalizar = true;
				break;

			case 2:
				finalizar = true;
				break;
				
			default:
				System.out.println("Opción incorrecta");
				System.out.println();
				System.out.println();
				break;
			}
		}
		sc.close();
	}

}
