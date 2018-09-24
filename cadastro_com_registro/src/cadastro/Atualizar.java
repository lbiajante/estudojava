package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import registro.CadastrarRegistro;
import utilitarias.Conexao;
import utilitarias.ValidaCPF;
import utilitarias.ValidaCelular;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;

public class Atualizar {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarCadastro() {

		CadastroPessoa cad = new CadastroPessoa();
		CadastrarRegistro reg = new CadastrarRegistro();
		Conexao con = new Conexao();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		boolean confere2 = true;
		boolean confere = true;
		boolean existe = false;

		String codigo = null;

		System.out.println("Digite uma opcao: ");
		System.out.println("1- Atualizar os dados de cadastro de pessoas");
		System.out
				.println("2- Adicionar registro de visita referente a um cadastro");
		String opAtualizar = entrada.nextLine();

		if (opAtualizar.equalsIgnoreCase("1")) {
			do {
				try {
					while (confere) {
						codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
						if (codigo.trim().equalsIgnoreCase("s")) {

							confere = false;
							confere2 = false;
						} else {
							codigo = validaId.confereID(codigo);

							String sql = "SELECT * FROM cadastro_de_pessoas";
							try {
								PreparedStatement ps = con.conexao()
										.prepareStatement(sql);
								ResultSet rs = ps.executeQuery();
								while (rs.next()) {
									cad.setPosicao(rs.getString("id"));
									if (cad.getPosicao().equals(codigo)) {
										existe = true;
										break;
									}
								}
								ps.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}

							if (existe) {

								String label = "Digite o nome atualizado";
								cad.setNome(string.texto(textInput(label),
										label));
								cad.setDataNascimento(data.data("Digite a data de nascimento com o formato: ddmmaaaa"));
								cad.setCpf(cpf.validarCPF());
								label = "Digite o nome da empresa atualizado";
								cad.setEmpresa(string.texto(textInput(label),
										label));
								label = "Digite a area de atuacao";
								cad.setAreaDeAtuacao(string.texto(
										textInput(label), label));
								cad.setCelular(celular.formatarCelular());

								String sql2 = "UPDATE cadastro_de_pessoas SET nome_pessoa = '"
										+ cad.getNome()
										+ "' , data_nasc = '"
										+ cad.getDataNascimento()
										+ "' , cpf = '"
										+ cad.getCpf()
										+ "' , celular = '"
										+ cad.getCelular()
										+ "' , empresa = '"
										+ cad.getEmpresa()
										+ "' , area_atuação = '"
										+ cad.getAreaDeAtuacao()
										+ "' WHERE id = '" + codigo + "';";
								System.out.println(sql2);
								try {
									PreparedStatement ps2 = con.conexao()
											.prepareStatement(sql2);
									ps2.execute();
									ps2.close();

								} catch (SQLException e) {
									e.printStackTrace();
								}
								confere = false;
								confere2 = false;

							} else if (existe == false) {
								System.out
										.println("Nao existe cadastro com esse ID para ser atualizado");
								confere = true;
								confere2 = true;
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.printf("Voce nao digitou um numero inteiro!\n");
					codigo = textInput("Digite um numero inteiro");
					confere2 = true;
				}
			} while (confere2);

		} else if (opAtualizar.equals("2")) {
			
		}
			
	}
}
