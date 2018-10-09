package uteis;

import conexao_cliente.GerenciadorDeClientes;

public class ValidaCelular {

	private String celularFormatado = null;
	private ValidaCPF cpf = new ValidaCPF();
	boolean confere = true;

	public String formatarCelular(GerenciadorDeClientes msg) {
		msg.enviaMensagem("Deseja cadastrar um celular? (S/N)?");
		String opcaoCelular = msg.recebeMensagem();

		if (opcaoCelular.equalsIgnoreCase("s")) {
			while (confere) {
				try {
					msg.enviaMensagem("Digite o celular, somente os numeros com DDD ((##)9####-####): ");
					String cf = msg.recebeMensagem().trim();
					if (cf.matches("[1-9][1-9][9][0-9]{8}")) {
						cf = cpf.formatString(cf, "(##) #####-####");
						celularFormatado = cf;
						confere = false;
					} else {
						msg.enviaMensagem("Numero em formato invalido");
						confere = true;
					}
				} catch (Exception e) {
					msg.enviaMensagem("Numero em formato invalido");
					confere = true;
				}
			}
		} else if (opcaoCelular.equalsIgnoreCase("n")) {
			celularFormatado = ("Celular nao cadastrado");
			msg.enviaMensagem("Sem telefone");
		} else {
			msg.enviaMensagem("Opcao invalida! Celular nao cadastrado!");
		}
		return celularFormatado;
	}
}
