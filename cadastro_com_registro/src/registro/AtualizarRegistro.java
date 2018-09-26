package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
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

		while (confere) {
			codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
			if (codigo.trim().equalsIgnoreCase("s")) {

				confere = false;
			} else {
				codigo = validaId.confereID(codigo);

				String sql = "SELECT * FROM registro_de_visitas";
				try {
					PreparedStatement ps = Conexao.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						regg.setPosicao(rs.getString("id"));
						if (regg.getPosicao().equals(codigo)) {
							existe = true;
							break;
						}
					}
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (existe) {

					regg.setLocal(cadLocal.cadastrarLocal());
					regg.setData(data
							.data("Atualize a data da visita com o formato: ddmmaaaa"));
					regg.setHora(data
							.hora("Atualize a hora da visita com o formato: hhmm"));
					String sql2 = "UPDATE registro_de_visitas SET data_visita = '"
							+ regg.getData()
							+ "' , hora_visita = '"
							+ regg.getHora()
							+ "' , lugar = '"
							+ regg.getLocal()
							+ "' WHERE id = '"
							+ codigo
							+ "';";
					try {
						PreparedStatement ps2 = Conexao.conexao()
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
