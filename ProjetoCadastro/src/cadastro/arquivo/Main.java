package cadastro.arquivo;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		boolean execute = true;
		Util util = new Util();
		Cadastrar cadastrar = new Cadastrar();
		CadastroEmArquivo cad = new CadastroEmArquivo();
		Remover remover = new Remover();
		Atualizar atualizar = new Atualizar();
		Listar listar = new Listar();
		Scanner entrada;

		System.out.println("BEM VINDO AO CADASTRO DE USUÁRIOS");
		System.out.println("");
		util.gerarArquivo();

		System.out.println("Selecione a opção:");
		System.out.println("1 - Novo cadastro");
		System.out.println("2 - Listar cadastros");
		System.out.println("3 - Remover cadastro");
		System.out.println("4 - Atualizar cadastro");
		System.out.println("5 - Sair");

		while (execute) {
			entrada = new Scanner(System.in);
			String opcao = entrada.next();
			if (opcao.equals("1")) {
				cadastrar.cadastrar();
			} else if (opcao.equals("2")) {
				listar.listarCadastros();
			} else if (opcao.equals("3")) {
				remover.removerCadastro();
			} else if (opcao.equals("4")) {
				atualizar.atualizarCadastro();
			} else if (opcao.equals("5")) {
				System.out.println("Programa de cadastro finalizado!");
				System.out.println("");
				execute = false;
			} else {
				System.out.println("Opção Inválida!!!");
			}
		}
	}
}
