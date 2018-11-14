package registro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import local.CadastrarLocal;
import utilitarias_cadastro_manual.ValidaData;
import conexao_cliente_manual.Gerenciador;

public class AtualizarRegistro {

	public void atualizarRegistro(Gerenciador msg) {

		RegistroVisita regg = new RegistroVisita();
		CadastrarLocal cadLocal = new CadastrarLocal();
		ValidaData data = new ValidaData();
		boolean confere = true;
		int x = 0;
		String codigo = null;

		msg.enviaMensagem("Atualizar registros");

		while (confere) {
			msg.enviaMensagem("Digite um ID para ser atualizado ou 's' para sair");
			codigo = msg.recebeMensagem();

			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				try {
					int cod = Integer.parseInt(codigo);
					String sql = "SELECT * FROM registro_de_visitas where id = "
							+ cod + ";";

					PreparedStatement ps = ConectaBD.getConnection()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						x++;
					}
					ps.close();
				} catch (NumberFormatException nfe) {
					System.out.println("Codigo em formato errado");
					confere = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (x != 0) {
					regg.setLocal(cadLocal.cadastrarLocal(msg));
					String labelOut = "Atualize a data da visita com o formato: ddmmaaaa";
					regg.setData(data.data(labelOut, msg));

					labelOut = "Atualize a hora da visita com o formato: hhmm";
					regg.setHora(data.hora(labelOut, msg));

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
						PreparedStatement ps2 = ConectaBD.getConnection()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (x == 0) {
					msg.enviaMensagem("Nao existe cadastro com esse ID para ser atualizado");
					confere = true;
				}
			}
		}
	}

}
