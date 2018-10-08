package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uteis.ConectaBD;

public class ListarLocais {
	ConectaBD con = new ConectaBD();
	Local local = new Local();
	String lista;
	public String listarLocais() {

		lista = "";
		System.out.println("Lista: locais");
		//SQL de listagem
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = ConectaBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {				
				local.setLugar(rs.getString("lugar"));
				lista += local.toString();
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

}
