package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaId;

public class Remover {

	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerCadastro() {
		ValidaId validaId = new ValidaId();
		CadastroPessoa cad = new CadastroPessoa();
		boolean existe = false;
		boolean confere2 = true;
		boolean confere = true;
		String codigo = null;
		System.out.println("Remover: cadastro de pessoas");

		do {
			try {
				while (confere) {
					codigo = textInput("Digite um ID para ser removido ou 's' para sair");
					if (codigo.trim().equalsIgnoreCase("s")) {
						confere = false;
						confere2 = false;
					} else {
						codigo = validaId.confereID(codigo);
						String sql = "SELECT * FROM cadastro_de_pessoas;";

						try {
							PreparedStatement ps = Conexao.conexao()
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
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (existe) {
							String sql2 = "DELETE FROM cadastro_de_pessoas WHERE id = '"
									+ codigo + "';";
							try {
								PreparedStatement ps2 = Conexao.conexao()
										.prepareStatement(sql2);
								ps2.execute();
								ps2.close();
								System.out.println("Cadastro removido");

							} catch (SQLException e) {
								System.out
										.println("Cadastro desta pessoa nao pode ser removido, \npois esta vinculado a um ou mais registros de visitas");
							}
							confere = false;
							confere2 = false;

						} else if (existe == false) {
							System.out
									.println("Nao existe cadastro com esse ID para ser removido");
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

	}
}
