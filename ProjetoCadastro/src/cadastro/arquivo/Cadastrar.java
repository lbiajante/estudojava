package cadastro.arquivo;

import java.util.ArrayList;
import java.util.Scanner;

public class Cadastrar {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);
	boolean cadastrando = true;	
	ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();

	public void cadastrar() {

		while (cadastrando) {
			System.out.println("Cadastro de Usuário");		
			util.contarLinhas();
			cad.setNome(textInput("Digite o nome: "));
			cad.setDataNascimento(data.data());
			cad.setCpf(cpf.validarCPF());
			cad.setCelular(celular.formatarCelular());
			

			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			if (cadastrar.equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				cadastroEmArquivo.add(cad);
				util.escreverNoArquivo();
				cadastrando = false;
			} else if (cadastrar.equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
			} else {
				System.out.println("Opção inválida, cadastro ignorado!");
			}

			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")) {
				cadastrando = true;
			} else {
				System.out.println("Opção inválida!");
				cadastrando = false;
			}
		}
	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}


}
