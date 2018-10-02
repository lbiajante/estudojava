package utilitarias;

import java.io.IOException;
import java.net.Socket;

import server.Server;


//classe main inclui o menu principal e os sub menus que chamam os métodos respectivos
public class Main {

	public static void main(String[] args) {
				
		try {
			Server server = new Server();
			System.out.println("Aguardando conexão...");
			server.criarConexao(3333);
			Socket socket = server.esperaConexao();
			System.out.println("Cliente conectado");
			server.trataConexao(socket);
			System.out.println("Cliente finalizado");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Menu menu = new Menu();
		boolean confere = true;		

		while (confere) {
			try {
				menu.menu();
				confere = false;
			} catch (NumberFormatException e) {
				System.out.println("Opcao invalida. Tente novamente!");
				confere = true;
			}
		}
	}
}
