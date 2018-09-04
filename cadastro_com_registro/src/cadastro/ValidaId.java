package cadastro;

import java.util.ArrayList;
import java.util.Scanner;

public class ValidaId {
	
	ArrayList<CadastroEmArquivo> lista = new ArrayList<CadastroEmArquivo>();
	CadastroEmArquivo cad = new CadastroEmArquivo();
	Scanner entrada = new Scanner(System.in);
	Util util = new Util();
	
	public int verificaCodigo(String codigo, String path) {
		
		int cod = 0;
		boolean confere = true;
		String confereCod = null;
		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
		list = util.lerArquivo(path, 1);	

		do {
			try {
				while (confere) {
					cod = Integer.parseInt(codigo.trim());
					if (cod <= 0) {
						System.out
								.println("O codigo precisa ser maior que zero");
						confere = true;
					} else {
						confereCod =  String.format("%06d", cod).trim();
						
						for (Object c: list){
							
							if (confereCod.equals(((CadastroEmArquivo)c).getPosicao())){
								codigo = textInput("Esse ID está sendo usado, por favor digite outro");
								confere = true;
								cod = 0;
							} else {
								confere = false;
							}
						}						
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um número inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				cod = 0;
			}
		} while (cod == 0);
		return cod;
	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
