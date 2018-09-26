package cadastro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import registro.CadastrarRegistro;
import utilitarias.Conexao;
import utilitarias.ValidaCPF;
import utilitarias.ValidaCelular;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;

public class Cadastrar {

	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();
	CadastrarRegistro cadReg = new CadastrarRegistro();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar() {

		System.out.println("Cadastro de Usuario");
		String table = "cadastro_de_pessoas";
		String label = "Digite o ID a ser cadastrado";
		cad.setPosicao(validaId.verificaID(textInput(label), table));
		label = "Digite o nome";
		cad.setNome(string.texto(textInput(label), label));
		cad.setDataNascimento(data
				.data("Digite a data de nascimento com o formato: ddmmaaaa"));
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
				String sql = "INSERT INTO cadastro_de_pessoas "
						+ "(id, nome_pessoa, data_nasc, cpf, celular, empresa, area_atuação) values"
						+ "( '" + cad.getPosicao() + "' , '" + cad.getNome()
						+ "' , '" + cad.getDataNascimento() + "' , '"
						+ cad.getCpf() + "' , '" + cad.getCelular() + "' , '"
						+ cad.getEmpresa() + "' , '" + cad.getAreaDeAtuacao()
						+ "' );";
				try {
					PreparedStatement ps = Conexao.conexao().prepareStatement(
							sql);
					ps.execute();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (cadastrar.trim().equalsIgnoreCase("s")) {
					System.out.println("Cadastro adicionado!");
					System.out.println(cad.toString());
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

			boolean conf = true;
			do {
				System.out.println("Deseja registrar uma visita? (S/N)");
				String opcaoVisita = null;
				opcaoVisita = entrada.nextLine();

				if (opcaoVisita.trim().equalsIgnoreCase("s")) {
					cadReg.cadastrar(cad.getPosicao(), cad.getNome());
					conf = true;

				} else if (opcaoVisita.equalsIgnoreCase("n")) {
					conf = false;
				} else {
					System.out.println("Opcao invalida! Tente novamente!");
					conf = true;
				}
			} while (conf);
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
