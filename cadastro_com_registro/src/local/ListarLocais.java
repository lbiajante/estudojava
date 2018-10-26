package local;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;

public class ListarLocais {
	ConectaBD con = new ConectaBD();
	Local local = new Local();
	String lista;

	public void listarLocais(Gerenciador msg) {

		lista = "";
		msg.enviaMensagem("Lista: locais");
		// SQL de listagem
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				local.setLugar(rs.getString("lugar"));
				lista += local.toString();
			}
			msg.enviaMensagem(lista);
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
