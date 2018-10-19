package dispositivo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uteis.ConectaBD;
import conexao_cliente.Gerenciador;

public class ValidaDispositivo {
	CadastrarRegistroDispositivo crd = new CadastrarRegistroDispositivo();

	public void validaDispositivo(Gerenciador msg) {

		boolean confere = true;

		String recebeDispositivo = msg.recebeMensagem();
		String id = "";

		while (confere) {

			String sql = "SELECT * FROM cadastro_de_pessoas WHERE id = "
					+ recebeDispositivo + ";";

			try {
				int x = 0;
				PreparedStatement ps = ConectaBD.conexao().prepareStatement(
						sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					x++;
				}
				if (x == 0) {
					System.out.println("Usuario inexistente");
					msg.enviaMensagem("::ERRO");
					confere = false;

				} else {
					rs.beforeFirst();
					while (rs.next()) {
						id = rs.getString("id");
						if (id.equals(recebeDispositivo)) {
							String nome = rs.getString("nome_pessoa");
							msg.enviaMensagem("::OK");
							crd.cadastrarRegistroDispositivo(id, nome, msg);

							rs.close();
							ps.close();
							confere = true;
							break;
						} else {
							confere = false;
							break;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
