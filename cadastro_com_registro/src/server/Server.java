package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;

	public void criarConexao(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	public Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	public void fechaConexao(Socket socket) throws IOException {
		socket.close();
	}

	public void trataConexao(Socket socket) throws IOException {
		try {
			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());			

			String msg = input.readUTF();
			System.out.println("Mensagem recebida..." + msg);
			output.writeUTF("Hello");
			output.flush();
			
			input.close();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.fechaConexao(socket);
		}
	}
}
