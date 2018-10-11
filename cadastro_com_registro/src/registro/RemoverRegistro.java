package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente.Gerenciador;
import uteis.ConectaBD;
import uteis.ValidaId;

public class RemoverRegistro {

	public void removerRegistro(Gerenciador msg) {
		ValidaId validaId = new ValidaId();
		RegistroVisita reg = new RegistroVisita();
		boolean existe = false;
		boolean confere = true;
		String codigo = null;

		while (confere) {

			msg.enviaMensagem("Digite um ID para ser removido ou 's' para sair");
			codigo = msg.recebeMensagem();

			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				codigo = validaId.confereID(codigo, msg);
				String sql = "SELECT * FROM registro_de_visitas";
				try {
					PreparedStatement ps = ConectaBD.conexao()
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
						PreparedStatement ps2 = ConectaBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						msg.enviaMensagem("Registro removido");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (existe == false) {
					msg.enviaMensagem("Nao existe registro com esse ID para ser removido");
					confere = true;
				}
			}
		}
	}
}
