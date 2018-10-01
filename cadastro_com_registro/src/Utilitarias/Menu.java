package utilitarias;

import java.util.Scanner;

import registro.AtualizarRegistro;
import registro.CadastrarRegistro;
import registro.ListarRegistro;
import registro.RemoverRegistro;
import local.CadastrarLocal;
import local.ListarLocais;
import local.RemoverLocal;
import cadastro.Atualizar;
import cadastro.Cadastrar;
import cadastro.Listar;
import cadastro.Remover;

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
	int opcao;
	int subOpcao;

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void menu() {
		
		switch (opcao = Integer
				.parseInt(textInput(
						"\nBEM VINDO AO CADASTRO DE PESSOAS E REGISTROS DE VISITAS\n"
								+ "\n---------------------------------------------------------------------------------------------\n"
								+ "Digite o numero da opcao selecionada:"
								+ "\n1 - Cadastrar pessoas/ Novos registros de visitas/ Novos locais\n"
								+ "2 - Listar cadastro de pessoas/ Listar registros de visitas/ Listar locais cadstrados\n"
								+ "3 - Apagar item do cadastro de pessoas/ Apagar registros de visitas/ Apagar locais\n"
								+ "4 - Atualizar cadastro de pessoas/ Atualizar registros de visitas\n"
								+ "5 - Sair\n"
								+ "---------------------------------------------------------------------------------------------")
						.trim())) {
		case 1:
			this.submenu(subOpcao = Integer
					.parseInt(textInput("Deseja cadastrar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais")));
			this.menu();
			break;
		case 2:
			this.submenu(subOpcao = Integer
					.parseInt(textInput("Deseja listar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais")));
			this.menu();
			break;
		case 3:
			this.submenu(subOpcao = Integer
					.parseInt(textInput("Deseja remover: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais")));
			this.menu();
			break;
		case 4:
			this.submenu(subOpcao = Integer
					.parseInt(textInput("Deseja atualizar: \n1 - Pessoas\n2 - Registros de Visitas\n ")));
			this.menu();
			break;
		case 5:
			System.out.println("Programa de cadastro finalizado!");			
			break;
		default:
			System.out.println("Opcao invalida. Tente novamente!");
			this.menu();
		}		
	}

	private void submenu(int subOpcao) {
		
		String s = opcao + "" + subOpcao;
		subOpcao = Integer.parseInt(s);

		switch (subOpcao) {
		case 11:
			cadastrar.cadastrar();
			break;
		case 12:
			cadRegistro.inserirRegistro();
			break;
		case 13:
			cadLocal.cadastrarLocal();
			break;
		case 21:
			listar.listarCadastros();
			break;
		case 22:
			listRegistro.listarRegistros();
			break;
		case 23:
			listLocal.listarLocais();
			break;
		case 31:
			remover.removerCadastro();
			break;
		case 32:
			remRegistro.removerRegistro();
			break;
		case 33:
			remLocal.removerLocal();
			break;
		case 41:
			atualizar.atualizarCadastro();
			break;
		case 42:
			atualRegistro.atualizarRegistro();
			break;
		default:
			System.out.println("Opcao invalida. Tente novamente!");
			this.menu();
		}
	}
}
