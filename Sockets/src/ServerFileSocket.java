import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.*;


public class ServerFileSocket {
	public final static int SOCKET_PORT = 13267;  // you may change this
	public final static String FILE_TO_SEND = "c:/temp/prueba.pdf";  // you may change this
	
	public static void main (String [] args ){
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		ServerSocket servsock = null;
		Socket sock = null;
		try {
			servsock = new ServerSocket(SOCKET_PORT);
			while (true) {
				System.out.println("Esperando...");
			
				sock = servsock.accept();
				System.out.println("Conexión aceptada : " + sock);
				// send file
				File myFile = new File (FILE_TO_SEND);
				byte [] mybytearray  = new byte [(int)myFile.length()];
				fis = new FileInputStream(myFile);
				bis = new BufferedInputStream(fis);
				bis.read(mybytearray,0,mybytearray.length);
				os = sock.getOutputStream();
				System.out.println("Enviando " + FILE_TO_SEND 
						+ "(" + mybytearray.length + " bytes)");
				os.write(mybytearray,0,mybytearray.length);
				os.flush();
				System.out.println("Done.");
			}	
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			try {
				if (bis != null) bis.close();
				if (os != null) os.close();
				if (sock!=null) sock.close();
				if (servsock != null) servsock.close();
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
	}
}
