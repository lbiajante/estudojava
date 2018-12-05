package conexao_dispositivo;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarLocalDispositivo {
	public String cadastrarLocal(String local) {
		int x = 0;
		String lugar = local;
		String sql = "SELECT * FROM local where lugar = '" + lugar + "';";

		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				x++;
				ps.close();
				rs.close();
				break;
			}
			if (x == 0) {
				sql = "INSERT INTO local " + "(lugar) values" + "( '"
						+ lugar + "' );";
				ps = ConectaBD.getConnection().prepareStatement(sql);
				ps.execute();				
			
			ps.close();
			rs.close();
			} else {
				System.out.println("caiu no else");
			}
			

		} catch (SQLException e) {
			System.out.println("erro no cadastrar local");
			e.printStackTrace();
		}
		return lugar;
	}
}
