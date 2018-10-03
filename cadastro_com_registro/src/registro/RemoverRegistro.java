package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaId;

public class RemoverRegistro {

	Scanner entrada = new Scanner(System.in);

	// método para impressão em tela e captura de entrada de dados do usuário
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
		// laço para garantir a entrada de um ID válido
		while (confere) {
			codigo = textInput("Digite um ID para ser removido ou 's' para sair");
			// opção de sair sem precisar completar a rotina de apagar um
			// registro
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				// método de validação de ID
				codigo = validaId.confereID(codigo);
				// SQL de listagem para verificar se o item a ser removido
				// existe
				String sql = "SELECT * FROM registro_de_visitas";
				try {// conexão com o BD
					PreparedStatement ps = Conexao.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					// laço para se buscar o item solicitado
					while (rs.next()) {
						reg.setPosicao(rs.getString("id"));
						// marcação de existência do item na tabela
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
				// rotina de exclusão do item solicitado e localizado
				if (existe) {
					// SQL de exclusão do item
					String sql2 = "DELETE FROM registro_de_visitas WHERE id = '"
							+ codigo + "';";
					try { // nova conexão com o BD
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
