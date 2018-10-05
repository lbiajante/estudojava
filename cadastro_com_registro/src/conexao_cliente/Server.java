package conexao_cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private Operacoes data = new Operacoes();

	private void criarServerSocket(int porta) throws IOException {
		data.serverSocket = new ServerSocket(porta);
	}

	private Socket esperaConexao() throws IOException {
		Socket socket = data.serverSocket.accept();
		return socket;
	}

	private void fechaSocket(Socket s) throws IOException {
		s.close();
	}

	private void enviaMsg(Object o, ObjectOutputStream out) throws IOException {
		out.writeObject(o);
		out.flush();
	}

	private void trataConexao(Socket socket) throws IOException,
			ClassNotFoundException {

		try {
			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());
			System.out.println("1");
			data.fazerOperacoes(input, output);
			
			input.close();
			output.close();
		} catch (IOException e) {
			
			System.out
					.println("Problema no tratamento da conexão com o cliente: "
							+ socket.getInetAddress());
			System.out.println("Erro: " + e.getMessage());
			throw e;
		} finally {		
			fechaSocket(socket);
		}
	}

	public void fazConexao() {
		try {
			Server server = new Server();
			server.criarServerSocket(5555);
			while (true) {
				System.out.println("Aguardando conexão...");
				Socket socket = server.esperaConexao();
				System.out.println("Cliente conectado.");

				server.trataConexao(socket);
				System.out.println("Cliente finalizado.");
			}
		} catch (IOException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
