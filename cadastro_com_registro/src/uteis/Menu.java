package uteis;

import java.net.ServerSocket;
import java.util.Scanner;

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
import conexao_cliente.GerenciadorDeClientes;
import conexao_cliente.ServidorSocket;

public class Menu {

	Scanner entrada = new Scanner(System.in);
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
	GerenciadorDeClientes msg = new GerenciadorDeClientes(client.server());

	int opcao;
	int subOpcao;

	public void menu() {

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
		switch (opcao = Integer.parseInt(msg.recebeMensagem())) {

		case 1:
			msg.enviaMensagem("Deseja cadastrar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
			this.submenu(subOpcao = Integer.parseInt(msg.recebeMensagem()));
			this.menu();
			break;
		case 2:
			msg.enviaMensagem("Deseja listar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
			this.submenu(subOpcao = Integer.parseInt(msg.recebeMensagem()));
			this.menu();
			break;
		case 3:
			msg.enviaMensagem("Deseja remover: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
			this.submenu(subOpcao = Integer.parseInt(msg.recebeMensagem()));
			this.menu();
			break;
		case 4:
			msg.enviaMensagem("Deseja atualizar: \n1 - Pessoas\n2 - Registros de Visitas\n ");
			this.submenu(subOpcao = Integer.parseInt(msg.recebeMensagem()));
			this.menu();
			break;
		case 5:
			msg.enviaMensagem("Cliente finalizado!");		
			break;
		case 6:
			msg.enviaMensagem("Cliente e servidor finalizados!");
			msg.fechaCliente();
			break;
		
		default:
			msg.enviaMensagem("Opcao invalida. Tente novamente!");
			this.menu();
		}
	}

	private void submenu(int subOpcao) {

		String s = opcao + "" + subOpcao;
		subOpcao = Integer.parseInt(s);

		switch (subOpcao) {
		case 11:
			cadastrar.cadastrar(msg);
			break;
		case 12:
			cadRegistro.inserirRegistro(msg);
			break;
		case 13:
			cadLocal.cadastrarLocal(msg);
			break;
		case 21:
			listar.listarCadastros(msg);
			break;
		case 22:
			listRegistro.listarRegistros(msg);
			break;
		case 23:
			listLocal.listarLocais(msg);
			break;
		case 31:
			remover.removerCadastro(msg);
			break;
		case 32:
			remRegistro.removerRegistro(msg);
			break;
		case 33:
			remLocal.removerLocal(msg);
			break;
		case 41:
			atualizar.atualizarCadastro(msg);
			break;
		case 42:
			atualRegistro.atualizarRegistro(msg);
			break;
		default:
			msg.enviaMensagem("Opcao invalida. Tente novamente!");
			this.menu();
		}
	}
}