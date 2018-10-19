package uteis;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import conexao_cliente.ServidorSocket;
import dispositivo.LerDispositivo;

public class Main {

	public static void main(String[] args) {
		boolean confere = true;
		int i = 0;
		int port = 0;
		Scanner entrada = new Scanner(System.in);
		
		while (confere) {
			try {				
				System.out.println("inicializando servidor...\n"
						+ "Indique o tipo de conexão:\n"
						+ "1 - Cliente administrador do banco\n"
						+ "2 - Dispositivo de ronda");
				i = entrada.nextInt();

				if (i == 1) {
					port = 5555;
					confere = false;
					break;

				} else if (i == 2) {
					port = 6666;
					confere = false;
					break;
				} else {
					System.out
							.println("opcao nao existe, por favor digite novamente");
					confere = true;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("opcao invalida, tente novamente ");
				confere = true;
			}
		}

		try {
			ServidorSocket servidor = new ServidorSocket();
			ServerSocket server = servidor.server(port);
			System.out.println("aguardando cliente ou dispositivo");
			
			while (true) {
				Socket cliente = server.accept();
				System.out.println("cliente ou dispositivo conectado");
				if (port == 5555){
				new Menu(cliente);
				} else if (port == 6666){
					new LerDispositivo(cliente);
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao conectar cliente ou dispositivo");

		}

	}
}
