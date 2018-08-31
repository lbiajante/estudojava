package cadastro;

import java.util.Scanner;

public class ValidaStrings {
	Scanner entrada = new Scanner (System.in);
	boolean confere = true;
//	String verificaNome;

	public String texto (String verificaTexto, String label){
		
		String campo = verificaTexto.trim();
		while (confere){
			
			if (campo.isEmpty()|| campo.trim().equals("")|| campo.trim().equals(null)) {
				System.out.println("O campo precisa ser preenchido");
				verificaTexto = textInput(label);
				confere = true;
				
			} else {
				confere = false;
			}
			campo = verificaTexto;
		}
		return campo;

	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
	

}
