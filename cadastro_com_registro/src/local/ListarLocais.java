package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import registro.RegistroVisita;
import utilitarias.Conexao;

public class ListarLocais {
	Conexao con = new Conexao();
	Local local = new Local();

	public void listarLocais() {

		System.out.println("Lista: locais");
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = con.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				local.setId(rs.getString("id"));
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
