package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilitarias.Conexao;

public class ListarLocais {
	Conexao con = new Conexao();
	Local local = new Local();

	public void listarLocais() {

		System.out.println("Lista: locais");
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = Conexao.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {				
				local.setLugar(rs.getString("lugar"));
				System.out.println(local.toString());
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
