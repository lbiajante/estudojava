package registro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;

public class RemoverRegistro {

	public void removerRegistro(Gerenciador msg) {
		int x = 0;
		int cod = 0;
		boolean confere = true;
		String codigo = null;

		while (confere) {

			msg.enviaMensagem("Digite um ID para ser removido ou 's' para sair");
			codigo = msg.recebeMensagem();

			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				try {
					cod = Integer.parseInt(codigo);
					String sql = "SELECT * FROM registro_de_visitas where id = "
							+ cod + ";";
					PreparedStatement ps = ConectaBD.getConnection()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						x++;
					}
					ps.close();
					rs.close();
				} catch (NumberFormatException nfe) {
					System.out.println("Codigo em formato errado");
					confere = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (x != 0) {
					String sql2 = "DELETE FROM registro_de_visitas WHERE id = '"
							+ cod + "';";
					try {
						PreparedStatement ps2 = ConectaBD.getConnection()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						msg.enviaMensagem("Registro removido");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (x == 0) {
					msg.enviaMensagem("Nao existe registro com esse ID para ser removido");
					confere = true;
				}
			}
		}
	}
}
