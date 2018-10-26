package conexao_cliente_manual;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
	Socket cliente = null;
	ServerSocket servidor = null;

	public ServerSocket server(int port) {
		try {
			System.out.println("startando o servidor");

			servidor = new ServerSocket(port);
			System.out.println("servidor startado");

		} catch (IOException e) {
			System.err.println("a porta está ocupada ou servidor foi fechado");
			e.printStackTrace();
		}
		return servidor;
	}

}
