package dispositivo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uteis.ConectaBD;
import conexao_cliente.Gerenciador;

public class CadastrarLocalDispositivo {

	public String cadastrarLocal(Gerenciador msg, String local) {
	
		boolean existe = true;

		
		String lugar = null;
		String sql = "SELECT * FROM local";

		try {
			PreparedStatement ps = ConectaBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lugar = rs.getString("lugar");
				if (lugar.equals(local)) {
					existe = false;
					break;
				}
			}
			lugar = local;
			if (existe == false) {			

			} else if (existe == true) {				
				sql = "INSERT INTO local " + "(lugar) values" + "( '"
						+ local.trim() + "' );";
				ps = ConectaBD.conexao().prepareStatement(sql);
				ps.execute();				
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lugar;
	}
}
