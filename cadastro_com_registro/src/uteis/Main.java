package uteis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import conexao_cliente.ServidorSocket;

public class Main {

	public static void main(String[] args) {

		try {
			ServidorSocket servidor = new ServidorSocket();
			ServerSocket server = servidor.server();
			
			while (true){
				Socket cliente = server.accept();	
				System.out.println("cliente conectado");
				new Menu(cliente);
			}
			
		} catch (IOException e) {
		System.err.println("Erro ao conectar");
			
		}

	}
}
