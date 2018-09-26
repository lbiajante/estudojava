package utilitarias;

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

//classe main inclui o menu principal e os sub menus que chamam os métodos respectivos
public class Main {

	public static void main(String[] args) {

		boolean execute = true;
		Cadastrar cadastrar = new Cadastrar();
		CadastrarRegistro cadRegistro = new CadastrarRegistro();
		CadastrarLocal cadLocal = new CadastrarLocal();
		Remover remover = new Remover();
		RemoverRegistro remRegistro = new RemoverRegistro();
		RemoverLocal remLocal = new RemoverLocal();
		Atualizar atualizar = new Atualizar();
		AtualizarRegistro atualRegistro = new AtualizarRegistro();
		Listar listar = new Listar();
		ListarRegistro listRegistro = new ListarRegistro();
		ListarLocais listLocal = new ListarLocais();
		Scanner entrada;
		String opcao = null;
		String subOpcao = null;

		System.out
				.println("BEM VINDO AO CADASTRO DE PESSOAS E REGISTROS DE VISITAS");
		System.out.println("");
		// menu principal
		while (execute) {
			entrada = new Scanner(System.in);
			System.out.println("");
			System.out
					.println("---------------------------------------------------------------------------------------------");
			System.out.println("Digite o numero da opcao selecionada:");
			System.out
					.println("1 - Cadastrar pessoas/ Novos registros de visitas/ Novos locais");
			System.out
					.println("2 - Listar cadastro de pessoas/ Listar registros de visitas/ Listar locais cadstrados");
			System.out
					.println("3 - Apagar item do cadastro de pessoas/ Apagar registros de visitas/ Apagar locais");
			System.out
					.println("4 - Atualizar cadastro de pessoas/ Atualizar registros de visitas"); // /
																									// Atualizar
																									// locais

			System.out.println("5 - Sair");
			System.out
					.println("---------------------------------------------------------------------------------------------");

			opcao = entrada.next();
			// submenu de cadastro
			if (opcao.equals("1")) {
				System.out
						.println("Deseja cadastrar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
				subOpcao = entrada.next();
				if (subOpcao.equals("1")) {
					cadastrar.cadastrar();
				} else if (subOpcao.equals("2")) {
					cadRegistro.inserirRegistro();
				} else if (subOpcao.equals("3")) {
					cadLocal.cadastrarLocal();
				}
				execute = true;
				// submenu de listagem
			} else if (opcao.equals("2")) {
				System.out
						.println("Deseja listar: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
				subOpcao = entrada.next();
				if (subOpcao.equals("1")) {
					listar.listarCadastros();
				} else if (subOpcao.equals("2")) {
					listRegistro.listarRegistros();
				} else if (subOpcao.equals("3")) {
					listLocal.listarLocais();
				}
				execute = true;
				// submenu de remoção
			} else if (opcao.equals("3")) {
				System.out
						.println("Deseja remover: \n1 - Pessoas\n2 - Registros de Visitas\n3 - Locais");
				subOpcao = entrada.next();
				if (subOpcao.equals("1")) {
					remover.removerCadastro();
				} else if (subOpcao.equals("2")) {
					remRegistro.removerRegistro();
				} else if (subOpcao.equals("3")) {
					remLocal.removerLocal();
				}
				execute = true;
				// submenu de atualização
			} else if (opcao.equals("4")) {
				System.out
						.println("Deseja atualizar: \n1 - Pessoas\n2 - Registros de Visitas\n "); // 3-
																									// Locais
				subOpcao = entrada.next();
				if (subOpcao.equals("1")) {
					atualizar.atualizarCadastro();
				} else if (subOpcao.equals("2")) {
					atualRegistro.atualizarRegistro();
				}

				execute = true;
			} else if (opcao.equals("5")) {
				System.out.println("Programa de cadastro finalizado!");
				System.out.println("");
				execute = false;
			} else {
				System.out.println("Opcao Invalida!");
			}
		}
	}
}
