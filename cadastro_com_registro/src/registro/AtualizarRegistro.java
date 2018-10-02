package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.ConexaoBD;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import local.CadastrarLocal;

public class AtualizarRegistro {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarRegistro() {

		RegistroVisita regg = new RegistroVisita();
		CadastrarLocal cadLocal = new CadastrarLocal();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		boolean confere = true;
		boolean existe = false;

		String codigo = null;

		System.out.println("Atualizar registros");
		// laço que espera a inserção de um ID válido
		while (confere) {
			codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
			// opção de sair sem ter que atualizar um registro para encerrar
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;

			} else {
				// método que valida o ID inserido
				codigo = validaId.confereID(codigo);
				// SQL de listagem dos itens da tabela registro de visitas
				String sql = "SELECT * FROM registro_de_visitas";
				try { // conexão com o BD
					PreparedStatement ps = ConexaoBD.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					// laço que traz todos os itens da tabela
					while (rs.next()) {
						regg.setPosicao(rs.getString("id"));
						// marcação se o item solicitado existe
						if (regg.getPosicao().equals(codigo)) {
							existe = true;
							break;
						}
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// rotina de solicitação de novos valores para a atualização do
				// item
				if (existe) {
					regg.setLocal(cadLocal.cadastrarLocal());
					regg.setData(data
							.data("Atualize a data da visita com o formato: ddmmaaaa"));
					regg.setHora(data
							.hora("Atualize a hora da visita com o formato: hhmm"));
					// SQL de atualização
					String sql2 = "UPDATE registro_de_visitas SET data_visita = '"
							+ regg.getData()
							+ "' , hora_visita = '"
							+ regg.getHora()
							+ "' , lugar = '"
							+ regg.getLocal()
							+ "' WHERE id = '"
							+ codigo
							+ "';";
					try { // nova conexão com o BD
						PreparedStatement ps2 = ConexaoBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (existe == false) {
					System.out
							.println("Nao existe cadastro com esse ID para ser atualizado");
					confere = true;
				}
			}
		}
	}

}
