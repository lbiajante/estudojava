package local;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;
import utilitarias_cadastro_manual.ValidaStrings;

public class RemoverLocal {

	public void removerLocal(Gerenciador msg) {
		ValidaStrings string = new ValidaStrings();
		Local local = new Local();
		boolean existe = false;
		boolean confere = true;
		String input = null;

		while (confere) {

			String labelOut = "Digite um lugar para ser removido ou 's' para sair";
			msg.enviaMensagem(labelOut);
			String labelIn = msg.recebeMensagem();
			input = (string.texto(labelIn, labelOut, msg));
			if (input.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				String sql = "SELECT * FROM local";
				try {
					PreparedStatement ps = ConectaBD.getConnection()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
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

				if (existe) {
					String sql2 = "DELETE FROM local WHERE lugar = '" + input
							+ "';";
					try {
						PreparedStatement ps2 = ConectaBD.getConnection()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						msg.enviaMensagem("Local removido");

					} catch (SQLException e) {
						msg.enviaMensagem("Local nao pode ser removido "
								+ "\nporque está vinculado a uma ou mais visitas");
					}
					confere = false;

				} else if (existe == false) {
					msg.enviaMensagem("O local indicado não existe para ser removido");
					confere = true;
				}
			}
		}
	}

	public String removerLocal(String input) {

		Local local = new Local();
		boolean existe = false;
		String mensagem = null;

		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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

		if (existe) {
			String sql2 = "DELETE FROM local WHERE lugar = '" + input + "';";
			try {
				PreparedStatement ps2 = ConectaBD.getConnection()
						.prepareStatement(sql2);
				ps2.execute();
				ps2.close();
				mensagem = "removido";

			} catch (SQLException e) {
				mensagem = "registro";
			}

		} else if (existe == false) {
			mensagem = "NLocal";
		}
		return mensagem;
	}
}