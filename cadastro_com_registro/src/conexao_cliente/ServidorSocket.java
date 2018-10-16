package conexao_cliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import uteis.Menu;

public class ServidorSocket {
	Socket cliente = null;
	ServerSocket servidor = null;

	public ServerSocket server() {
		try {
			System.out.println("startando o servidor");
			servidor = new ServerSocket(5555);
			System.out.println("servidor startado");				
			
		} catch (IOException e) {
			System.err.println("a porta está ocupada ou servidor foi fechado");
			e.printStackTrace();
		}
		return servidor;
	}
	
}
