package cadastro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cadastrar {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar(String path) {

		ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();

		System.out.println("Cadastro de Usuario");
		Id id = new Id(validaId.verificaCodigo(textInput("Digite o ID a ser cadastrado"), path));
		cad.setPosicao(id.getId());
		String label = "Digite o nome";
		cad.setNome(string.texto(textInput(label), label));
		cad.setDataNascimento(data.data());
		cad.setCpf(cpf.validarCPF());
		label = "Digite o nome da empresa";
		cad.setEmpresa(string.texto(textInput(label), label));
		label = "Digite a area de atuacao";
		cad.setAreaDeAtuacao(string.texto(textInput(label), label));
		cad.setCelular(celular.formatarCelular());
		String cadastrar = textInput("Adicionar cadastro (S/N)?");
		boolean confere = true;
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				System.out.println(cad.toString());
				cadastroEmArquivo.add(cad);
				util.escreverNoArquivo(cad, cadastroEmArquivo, path);
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				System.out.println("Cadastro ignorado!");
				confere = false;
			} else {
				System.out.println("Opcao invalida");
				cadastrar = textInput("Digite uma opcao valida. (S/N)");
				confere = true;
			}
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
