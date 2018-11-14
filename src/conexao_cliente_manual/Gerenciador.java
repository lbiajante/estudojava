package conexao_cliente_manual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Gerenciador {

	private Socket cliente;
	private BufferedReader leitor;
	private PrintWriter escritor;

	public Gerenciador(Socket cliente) {
		this.cliente = cliente;
	}

	public void enviaMensagem(String label) {

		try {
			escritor = new PrintWriter(cliente.getOutputStream());
			escritor.println(label);
			escritor.flush();

		} catch (IOException e) {
			System.err.println("o cliente fechou");
			e.printStackTrace();
		}
	}

	public String recebeMensagem() {
		String msg = null;
		try {
			leitor = new BufferedReader(new InputStreamReader(
					cliente.getInputStream()));
			msg = leitor.readLine();
			if (msg.equalsIgnoreCase("sair")) {
				cliente.close();
			}
		} catch (IOException e) {
			System.err.println("o cliente caiu");

		}
		return msg;
	}
}
