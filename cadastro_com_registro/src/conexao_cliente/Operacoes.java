package conexao_cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import cadastro.Listar;
import uteis.Menu;

public class Operacoes {
	public ServerSocket serverSocket;

	Menu menu = new Menu();
	Listar listar = new Listar();

	public void fazerOperacoes(ObjectInputStream input,
			ObjectOutputStream output) throws ClassNotFoundException,
			IOException {
		// Referencia estado = Referencia.CONECTADO;
		try {
			String subMenu = null;

			MensagemObj envioCliente1 = new MensagemObj(menu.menuPrincipal);
			output.writeObject(envioCliente1);

			MensagemObj recebeCliente1 = (MensagemObj) input.readObject();
			Integer
			opcaoMenuPrincipal = (Integer) recebeCliente1
					.getParam(Referencia.MENU);

			switch (opcaoMenuPrincipal) {
			case 1:
				subMenu = menu.subMenuCadastrar;
				break;
			case 2:
				subMenu = menu.subMenuListar;
				break;
			case 3:
				subMenu = menu.subMenuRemover;
				break;
			case 4:
				subMenu = menu.subMenuAtualizar;
				break;
			case 5:
				subMenu = menu.subMenuSair;
				break;
			default:
				System.out.println("Opção Inválida");
			}

			MensagemObj envioCliente2 = new MensagemObj(subMenu);
			output.writeObject(envioCliente2);

			MensagemObj recebeCliente2 = (MensagemObj) input.readObject();
			String opcaoSubMenu = (String) recebeCliente2
					.getParam(Referencia.SUBMENU);
			MensagemObj envioCliente3 = new MensagemObj(
					listar.listarCadastros());
			output.writeObject(envioCliente3);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
