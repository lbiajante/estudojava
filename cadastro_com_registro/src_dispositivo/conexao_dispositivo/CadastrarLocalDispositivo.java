package conexao_dispositivo;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarLocalDispositivo {
	public String cadastrarLocal(String local) {	
		int x= 0;
		String lugar = local;
		String sql = "SELECT * FROM local where lugar = '" + local +"';";

		try {
			PreparedStatement ps = ConectaBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				x++;
				}				
			if (x!=0) {	
			} else if (x == 0) {				
				sql = "INSERT INTO local " + "(lugar) values" + "( '"
						+ local.trim() + "' );";				
				ps.executeUpdate(sql);
				ps.close();
			}
			ps.close();	
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lugar;
	}
}
