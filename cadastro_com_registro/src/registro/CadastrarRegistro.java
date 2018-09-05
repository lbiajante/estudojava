package registro;

import java.util.ArrayList;
import java.util.Scanner;


public class CadastrarRegistro {

	RegistroEmArquivo reg = new RegistroEmArquivo();
	ValidaDataRegistro data = new ValidaDataRegistro();	
	ValidaStringsRegistro string = new ValidaStringsRegistro();
	ValidaIdRegistro validaId = new ValidaIdRegistro();
	UtilRegistro util = new UtilRegistro();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar(String path) {
		
		String pathReg = path;
		String[] splitted = pathReg.split("\\.");
		pathReg = splitted[0];
		pathReg = pathReg + "registro.txt";

		ArrayList<RegistroEmArquivo> registroEmArquivo = new ArrayList<RegistroEmArquivo>();

		System.out.println("Cadastro de Registro");
		IdRegistro id = new IdRegistro(validaId.verificaID(textInput("Digite o ID do local visitado"), pathReg));
		reg.setPosicao(id.getId());
		String label = "Digite o local";
		reg.setLocal(string.texto(textInput(label), label));
		reg.setData(data.data());
		reg.setHora(data.hora());
		String cadastrar = textInput("Adicionar cadastro (S/N)?");
		boolean confere = true;
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				System.out.println("Cadastro adicionado!");
				System.out.println(reg.toString());
				registroEmArquivo.add(reg);
				util.escreverNoArquivo(reg, registroEmArquivo, pathReg);
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
