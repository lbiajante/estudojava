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
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar(String path){
		
		 ArrayList<Object> cadastroEmArquivo = new ArrayList<Object>();
				 //Util.lerArquivo(path);
		boolean cadastrando = true;
		String nomez, dataz, cpfz, empresaz, areaz, celularz, idz;

		while (cadastrando) {

			System.out.println("Cadastro de Usuario");			
			textInput("");
			Id id = new Id(util.verificaCodigo(textInput("Digite o ID a ser cadastrado")));
			cad.setPosicao(id.getId());
			idz = cad.getPosicao();
			String label = "Digite o nome";
			cad.setNome(string.texto(textInput(label), label));
			nomez = cad.getNome();
			cad.setDataNascimento(data.data());
			dataz = cad.getDataNascimento();
			cad.setCpf (cpf.validarCPF());
			cpfz = cad.getCpf();
			label = "Digite o nome da empresa";
			cad.setEmpresa(string.texto(textInput(label), label));
			empresaz = cad.getEmpresa();
			label = "Digite a area de atuacao";
			cad.setAreaDeAtuacao(string.texto(textInput(label), label));
			areaz = cad.getAreaDeAtuacao();
			cad.setCelular(celular.formatarCelular());
			celularz = cad.getCelular();
			String cadastrar = textInput("Adicionar cadastro (S/N)?");
			boolean confere = true;
			while (confere) {
				if (cadastrar.trim().equalsIgnoreCase("s")) {
					System.out.println("Cadastro adicionado!");
					System.out.println(cad.toString());
					cadastroEmArquivo.add(new CadastroEmArquivo(path, nomez, dataz, cpfz, empresaz, areaz, celularz, idz));					
					cadastrando = false;
					confere = false;
				} else if (cadastrar.trim().equalsIgnoreCase("n")) {
					System.out.println("Cadastro ignorado!");
					confere = false;
					cadastrando = true;
				} else {
					System.out.println("Opcao invalida");
					cadastrar = textInput("Digite uma opcao valida. (S/N)");
					confere = true;
				}
			}
			Util.escreverNoArquivo(cadastroEmArquivo, path);
			String continua = textInput("Continuar cadastrando (S/N)?");
			if (continua.equalsIgnoreCase("n")) {
				cadastrando = false;
			} else if (continua.equalsIgnoreCase("s")) {
				cadastrando = true;
			} else {
				System.out.println("Opcao invalida!");
				cadastrando = false;
			}
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
