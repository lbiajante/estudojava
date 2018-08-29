package cadastro.arquivo;

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
					System.out.println("Digite o número de Celular: ");
					String cf = entrada.next();
					if (cf.matches("[1-9][1-9][2-9][0-9]{8}")) {
						cf = cpf.formatString(cf, "(##) #####-####");
						celularFormatado = cf;
						System.out.println(celularFormatado);
						confere = false;
					} else {
						System.out.println("Número em formato inválido");
						confere = true;
					}
				} catch (Exception e) {
					System.out.println("Número em formato inválido");
					confere = true;
				}
			}
		} else if (opcaoCelular.equalsIgnoreCase("n")) {
			celularFormatado = (null);
			System.out.println("Sem telefone. Aperte Enter");
		} else {
			System.out.println("Opção inválida! Celular não cadastrado!");
		}
		return celularFormatado;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
