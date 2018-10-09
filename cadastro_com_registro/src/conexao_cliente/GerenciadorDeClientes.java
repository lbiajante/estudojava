package conexao_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeClientes extends Thread {

	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		start();
	}

	public void enviaMensagem(String label) {

		try {
			escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor.println(label);
		} catch (IOException e) {
			System.err.println("o cliente fechou a conexao");
			e.printStackTrace();
		}
	}

	public String recebeMensagem() {
		String msg = null;
		try {
			leitor = new BufferedReader(new InputStreamReader(
					cliente.getInputStream()));
			msg = leitor.readLine();

		} catch (IOException e) {
			System.err.println("o cliente fechou a conexao");			
			e.printStackTrace();
		}
		return msg;
	}

	public void fechaCliente() {
		try {
			cliente.close();			
			
		} catch (IOException e) {
			System.out.println("problema em encerrar cliente");
			e.printStackTrace();
		}
	}
	public PrintWriter getEscritor() {
		return escritor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

}
