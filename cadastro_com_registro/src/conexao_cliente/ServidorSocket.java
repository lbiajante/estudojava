package conexao_cliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import uteis.Menu;

public class ServidorSocket {
	Socket cliente = null;
	ServerSocket servidor = null;

	public Socket server() {
		try {
			System.out.println("startando o servidor");
			servidor = new ServerSocket(5555);
			System.out.println("servidor startado");
		//	cliente = servidor.accept();
			
				this.startSocket();
			
		} catch (IOException e) {
			System.err.println("a porta está ocupada ou servidor foi fechado");
			e.printStackTrace();
		}
		return cliente;
	}
	
	public void startSocket () {
		try {
			cliente = servidor.accept();
		} catch (IOException e) {
			System.out.println("problema no start");
			e.printStackTrace();
		}
	}

	public void fechaSocket() {
		if (servidor != null) {
			try {
				servidor.close();
				cliente.close();				
			} catch (IOException e1) {				
				e1.printStackTrace();
			}
		}
	}
}
