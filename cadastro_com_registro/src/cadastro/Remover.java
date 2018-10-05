package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import uteis.ConectaBD;
import uteis.ValidaId;

public class Remover {

	Scanner entrada = new Scanner(System.in);
	//método para impressão em tela e captura de entrada de dados do usuário
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerCadastro() {
		ValidaId validaId = new ValidaId();
		CadastroPessoa cad = new CadastroPessoa();
		String codigo = null;
		System.out.println("Remover: cadastro de pessoas");
		// laço para garantir que se entre com um ID válido
		boolean confere = true;
		while (confere) {
			codigo = textInput("Digite um ID para ser removido ou 's' para sair");
			// opção de se sair sem a obrigação de concluir uma remoção de item
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				// método de verificação de validade de ID
				codigo = validaId.confereID(codigo);
				// SQL para verificar se o ID solicitado existe no BD
				String sql = "SELECT * FROM cadastro_de_pessoas;";
				boolean existe = false;
				try {
					PreparedStatement ps = ConectaBD.conexao().prepareStatement(
							sql); // conexão com o BD
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						// marcação se existir o cadastro solicitado
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
				// rotina para apagar o cadastro
				if (existe) {
					// SQL para apagar o cadastro solicidado
					String sql2 = "DELETE FROM cadastro_de_pessoas WHERE id = '"
							+ codigo + "';";
					try { // nova conexão com o BD
						PreparedStatement ps2 = ConectaBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						System.out.println("Cadastro removido");

					} catch (SQLException e) {
						// cadastros vinculados a registros de visitas por
						// foreign key lançarão SQLException
						System.out
								.println("Cadastro desta pessoa nao pode ser removido, "
										+ "\npois esta vinculado a um ou mais registros de visitas");
					}
					confere = false;

				} else if (existe == false) {
					System.out
							.println("Nao existe cadastro com esse ID para ser removido");
					confere = true;
				}
			}
		}
	}
}
