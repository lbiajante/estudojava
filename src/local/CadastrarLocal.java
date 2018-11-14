package local;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilitarias_cadastro_manual.ValidaStrings;
import conexao_cliente_manual.Gerenciador;

public class CadastrarLocal {

	Local cadLocal = new Local();
	ValidaStrings string = new ValidaStrings();
	String lugar = null;

	public String cadastrarLocal(Gerenciador msg) {
		msg.enviaMensagem("Cadastro de lugares");
		boolean existe = true;

		String labelOut = "Digite o lugar";
		msg.enviaMensagem(labelOut);
		String labelIn = msg.recebeMensagem();
		lugar = (string.texto(labelIn, labelOut, msg));

		String sql = "SELECT * FROM local";

		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				cadLocal.setLugar(rs.getString("lugar"));
				if (cadLocal.getLugar().equals(lugar)) {
					existe = false;
					break;
				}
			}
			if (existe == false) {
				msg.enviaMensagem("Lugar já cadastrado");
				lugar = cadLocal.getLugar();

			} else if (existe == true) {
				msg.enviaMensagem("Lugar não cadastrado ainda!");
				sql = "INSERT INTO local " + "(lugar) values" + "( '"
						+ lugar.trim() + "' );";
				ps = ConectaBD.getConnection().prepareStatement(sql);
				ps.execute();
				msg.enviaMensagem("Ok! Lugar adicionado!");
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lugar;
	}

}
