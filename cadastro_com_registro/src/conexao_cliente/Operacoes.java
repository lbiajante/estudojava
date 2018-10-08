package conexao_cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

import uteis.Menu;
import cadastro.Listar;

public class Operacoes {
	public ServerSocket serverSocket;

	Menu menu = new Menu();
	Listar listar = new Listar();

	public Integer tratarMenuPrincipal(ObjectInputStream input,
			ObjectOutputStream output) throws ClassNotFoundException,
			IOException {

		Integer opcaoMenuPrincipal = 0;
		MensagemObj envioCliente = new MensagemObj();
		MensagemObj recebeCliente;

		envioCliente.setParam(Referencia.PRINCIPAL, menu.menuPrincipal);
		output.writeObject(envioCliente);
		output.flush();

		recebeCliente = (MensagemObj) input.readObject();
		opcaoMenuPrincipal = (Integer) Integer.parseInt((String) recebeCliente
				.getParam(Referencia.MENU));

		if (opcaoMenuPrincipal != 5) {

			envioCliente.setParam(Referencia.SUBMENU,
					menu.listaSubMenu(opcaoMenuPrincipal));
			// MensagemObj envioCliente2 = new MensagemObj(Referencia.SUBMENU,
			// menu.listaSubMenu(opcaoMenuPrincipal));
			envioCliente.setStatus(Status.OK);
			output.writeObject(envioCliente);
			output.flush();
			recebeCliente = (MensagemObj) input.readObject();

			Integer opcaoSubMenu = (Integer) Integer
					.parseInt((String) recebeCliente
							.getParam(Referencia.SUBMENU));
			String s = opcaoMenuPrincipal + "" + opcaoSubMenu;
			opcaoSubMenu = Integer.parseInt(s);

			envioCliente.setParam(Referencia.LISTAR,
					menu.submenu(input, output, opcaoSubMenu));
			output.writeObject(envioCliente);
			output.flush();
		} else {
		}
		return opcaoMenuPrincipal;
	}

}
