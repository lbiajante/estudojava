package conexao_cliente_manual;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatDispositivoServer {
	Scanner entrada;

	public ChatDispositivoServer() {
		ServerSocket server;

		try {
			server = new ServerSocket(6666);
			while (true) {
				Socket socket = server.accept();
				new Thread(new EscutaCliente(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class EscutaCliente implements Runnable {
		public EscutaCliente(Socket socket) {
			try {
				entrada = new Scanner(socket.getInputStream());
			} catch (Exception e) {
			}
		}

		@Override
		public void run() {
			String texto;
			while ((texto = entrada.nextLine()) != null) {
				System.out.println(texto);
			}
		}
	}

	public static void main(String[] args) {
		new ChatDispositivoServer();

	}

}
