package uteis;

import conexao_cliente.Server;


//classe main inclui o menu principal e os sub menus que chamam os métodos respectivos
public class Main {

	public static void main(String[] args) {

		//Menu menu = new Menu();
		boolean confere = true;
		
		Server server = new Server();
		server.fazConexao();

		while (confere) {
			try {
				//menu.menu();
				confere = false;
			} catch (NumberFormatException e) {
				System.out.println("Opcao invalida. Tente novamente!");
				confere = true;
			}
		}
	}
}
