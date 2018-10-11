package uteis;

import java.util.Scanner;

import conexao_cliente.Gerenciador;

public class ValidaStrings {
	Scanner entrada = new Scanner(System.in);
	
	public String texto(String verificaTexto, String label, Gerenciador msg) {
		boolean confere = true;

		String campo = verificaTexto.trim();
		while (confere) {
			if (campo.trim().isEmpty() || campo.trim().equals("")
					|| campo.trim().equals(null)) {
				msg.enviaMensagem("O campo precisa ser preenchido");
				msg.enviaMensagem(label);				
				verificaTexto = msg.recebeMensagem();
				confere = true;
			} else {
				confere = false;
			}
			campo = verificaTexto;
		}
		return campo;
	}	
}
