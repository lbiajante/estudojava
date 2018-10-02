package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.ConexaoBD;
import utilitarias.ValidaStrings;

public class RemoverLocal {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerLocal() {
		ValidaStrings string = new ValidaStrings();
		Local local = new Local();
		boolean existe = false;
		boolean confere = true;
		String input = null;

		while (confere) {
			// laço que confere se o item exuste no BD
			String label = "Digite um lugar para ser removido ou 's' para sair";
			input = (string.texto(textInput(label), label));
			if (input.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				// SQL de listagem
				String sql = "SELECT * FROM local";
				try { // conexão com o BD
					PreparedStatement ps = ConexaoBD.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						// laço que verifica na tabela se o item existe
						local.setLugar(rs.getString("lugar"));
						if (local.getLugar().equals(input)) {
							existe = true;
							break;
						}
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// rotina de exclusão do item validado
				if (existe) {
					String sql2 = "DELETE FROM local WHERE lugar = '" + input
							+ "';";
					try {// nova conexão com o BD
						PreparedStatement ps2 = ConexaoBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						System.out.println("Local removido");

					} catch (SQLException e) {
						// O local que está vinculado ao registro de visitas por
						// foreign key e lança SQLException se tentar se excluir
						System.out
								.println("Local nao pode ser removido "
										+ "\nporque está vinculado a uma ou mais visitas");
					}
					confere = false;

				} else if (existe == false) {
					System.out
							.println("O local indicado não existe para ser removido");
					confere = true;
				}
			}
		}
	}

}
