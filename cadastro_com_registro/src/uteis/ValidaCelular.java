package uteis;

import java.util.Scanner;

public class ValidaCelular {

	private Scanner entrada = new Scanner(System.in);
	private String celularFormatado = null;
	private ValidaCPF cpf = new ValidaCPF();
	boolean confere = true;

	public String formatarCelular() {
		String opcaoCelular = textInput("Deseja cadastrar um celular? (S/N)?");
		if (opcaoCelular.equalsIgnoreCase("s")) {
			while (confere) {
				try {
					System.out
							.println("Digite o celular, somente os numeros com DDD ((##)9####-####): ");
					String cf = entrada.nextLine().trim();
					// gera uma máscara para validar o celular
					if (cf.matches("[1-9][1-9][9][0-9]{8}")) {
						cf = cpf.formatString(cf, "(##) #####-####");
						celularFormatado = cf;
						confere = false;
					} else {
						System.out.println("Numero em formato invalido");
						confere = true;
					}
				} catch (Exception e) {
					System.out.println("Numero em formato invalido");
					confere = true;
				}
			}
			// opção de não cadastrar um celular (único item não obrigatório)
		} else if (opcaoCelular.equalsIgnoreCase("n")) {
			celularFormatado = ("Celular nao cadastrado");
			System.out.println("Sem telefone");
		} else {
			System.out.println("Opcao invalida! Celular nao cadastrado!");
		}
		return celularFormatado;
	}

	// método para impressão em tela e captura de entrada de dados do usuário
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
