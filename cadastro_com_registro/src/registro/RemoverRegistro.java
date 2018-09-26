package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaId;

public class RemoverRegistro {

	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerRegistro() {
		ValidaId validaId = new ValidaId();
		RegistroVisita reg = new RegistroVisita();
		boolean existe = false;
		boolean confere = true;
		String codigo = null;

		
				while (confere) {
					codigo = textInput("Digite um ID para ser removido ou 's' para sair");
					if (codigo.trim().equalsIgnoreCase("s")) {
						confere = false;
					} else {
						codigo = validaId.confereID(codigo);

						String sql = "SELECT * FROM registro_de_visitas";
						try {
							PreparedStatement ps = Conexao.conexao()
									.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								reg.setPosicao(rs.getString("id"));
								if (reg.getPosicao().equals(codigo)) {
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
							String sql2 = "DELETE FROM registro_de_visitas WHERE id = '"
									+ codigo + "';";
							try {
								PreparedStatement ps2 = Conexao.conexao()
										.prepareStatement(sql2);
								ps2.execute();
								ps2.close();
								System.out.println("Registro removido");

							} catch (SQLException e) {
								e.printStackTrace();
							}
							confere = false;

						} else if (existe == false) {
							System.out
							.println("Nao existe registro com esse ID para ser removido");
							confere = true;
						}
					}
				}
			
	}
}
