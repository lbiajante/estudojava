package conexao_cliente;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorSocket {
	
	public Socket server () {
		Socket cliente = null;
		ServerSocket servidor = null;
		try {
			System.out.println("startando o servidor");
			servidor = new ServerSocket(5555);
			System.out.println("servidor startado");			
				 cliente = servidor.accept();			
			
		} catch (IOException e) {			
			try {
				if(servidor != null)
					servidor.close();
			} catch (IOException e1) {}
			
			System.err.println("a porta está ocupada ou servidor foi fechado");
			e.printStackTrace();
		}
		return cliente;		
	}
	
}
