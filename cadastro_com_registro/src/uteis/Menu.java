package uteis;

import local.CadastrarLocal;
import local.ListarLocais;
import local.RemoverLocal;
import registro.AtualizarRegistro;
import registro.CadastrarRegistro;
import registro.ListarRegistro;
import registro.RemoverRegistro;
import cadastro.Atualizar;
import cadastro.Cadastrar;
import cadastro.Listar;
import cadastro.Remover;
import conexao_cliente.Gerenciador;
import conexao_cliente.ServidorSocket;

public class Menu {

	Cadastrar cadastrar = new Cadastrar();
	CadastrarRegistro cadRegistro = new CadastrarRegistro();
	CadastrarLocal cadLocal = new CadastrarLocal();
	Listar listar = new Listar();
	ListarRegistro listRegistro = new ListarRegistro();
	ListarLocais listLocal = new ListarLocais();
	Remover remover = new Remover();
	RemoverRegistro remRegistro = new RemoverRegistro();
	RemoverLocal remLocal = new RemoverLocal();
	Atualizar atualizar = new Atualizar();
	AtualizarRegistro atualRegistro = new AtualizarRegistro();
	ServidorSocket client = new ServidorSocket();
	Gerenciador msg = new Gerenciador(client.server());
	String mensagemSubmenu = null;
	int opcao;
	int subOpcao;

	public void menu() {

		boolean confere = true;
		while (confere) {	

			msg.enviaMensagem("\n \n \n \n"
					+ "BEM VINDO AO CADASTRO DE PESSOAS E REGISTROS DE VISITAS\n"
					+ "\n---------------------------------------------------------------------------------------------\n"
					+ "Digite o numero da opcao selecionada:"
					+ "\n1 - Cadastrar pessoas/ Novos registros de visitas/ Novos locais\n"
					+ "2 - Listar cadastro de pessoas/ Listar registros de visitas/ Listar locais cadstrados\n"
					+ "3 - Apagar item do cadastro de pessoas/ Apagar registros de visitas/ Apagar locais\n"
					+ "4 - Atualizar cadastro de pessoas/ Atualizar registros de visitas\n"
					+ "5 - Encerrar cliente\n"
					+ "6 - Encerrar cliente e servidor\n"
					+ "---------------------------------------------------------------------------------------------");
//			if (msg.recebeMensagem().equalsIgnoreCase("sair")){
//				msg.fechaCliente();
//				client.startSocket();
//			}else {
			try {
				switch (opcao = Integer.parseInt(msg.recebeMensagem())) {

				case 1:
					mensagemSubmenu = "Deseja cadastrar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
					msg.enviaMensagem(mensagemSubmenu);
					this.submenu(subOpcao = Integer.parseInt(msg
							.recebeMensagem()));
					this.menu();
					confere = false;
					break;
				case 2:
					mensagemSubmenu = "Deseja listar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
					msg.enviaMensagem(mensagemSubmenu);
					this.submenu(subOpcao = Integer.parseInt(msg
							.recebeMensagem()));
					this.menu();
					confere = false;
					break;
				case 3:
					mensagemSubmenu = "Deseja remover: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais";
					msg.enviaMensagem(mensagemSubmenu);
					this.submenu(subOpcao = Integer.parseInt(msg
							.recebeMensagem()));
					this.menu();
					confere = false;
					break;
				case 4:
					mensagemSubmenu = "Deseja atualizar: \n1 - Pessoas\n2 - Registros de Visitas\n ";
					msg.enviaMensagem(mensagemSubmenu);
					this.submenu(subOpcao = Integer.parseInt(msg
							.recebeMensagem()));
					this.menu();
					confere = false;
					break;
				case 5:
					mensagemSubmenu = "Cliente finalizado!";
					msg.enviaMensagem(mensagemSubmenu);
					confere = false;
					break;
				case 6:
					mensagemSubmenu = "Cliente e servidor finalizados!";
					msg.enviaMensagem(mensagemSubmenu);

					confere = false;
					break;
				default:
					msg.enviaMensagem("Opcao invalida, escolha de 1 a 6. Tente novamente!");
					confere = true;
					this.menu();
				}
			} catch (NumberFormatException e) {
				msg.enviaMensagem("A opcao precisa ser numerica de 1 a 6. Tente novamente.");
				confere = true;
			}
		}
		//}
	}

	private void submenu(int subOpcao) {

		boolean confere = true;
		while (confere) {

			try {
				String s = opcao + "" + subOpcao;
				subOpcao = Integer.parseInt(s);

				switch (subOpcao) {
				case 11:
					confere = false;
					cadastrar.cadastrar(msg);
					break;
				case 12:
					confere = false;
					cadRegistro.inserirRegistro(msg);
					break;
				case 13:
					confere = false;
					cadLocal.cadastrarLocal(msg);
					break;
				case 21:
					confere = false;
					listar.listarCadastros(msg);
					break;
				case 22:
					confere = false;
					listRegistro.listarRegistros(msg);
					break;
				case 23:
					confere = false;
					listLocal.listarLocais(msg);
					break;
				case 31:
					confere = false;
					remover.removerCadastro(msg);
					break;
				case 32:
					confere = false;
					remRegistro.removerRegistro(msg);
					break;
				case 33:
					confere = false;
					remLocal.removerLocal(msg);
					break;
				case 41:
					confere = false;
					atualizar.atualizarCadastro(msg);
					break;
				case 42:
					confere = false;
					atualRegistro.atualizarRegistro(msg);
					break;
				default:
					confere = true;
					msg.enviaMensagem("Opcao invalida. Tente novamente!");
					msg.enviaMensagem("\n" + mensagemSubmenu + "\n");
					subOpcao = Integer.parseInt(msg.recebeMensagem());
				}
			} catch (NumberFormatException e) {
				msg.enviaMensagem("Opcao invalida. Tente novamente.");
				msg.enviaMensagem("\n" + mensagemSubmenu + "\n");
				subOpcao = Integer.parseInt(msg.recebeMensagem());
				confere = true;
			}
		}
	}
}