package dispositivo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import uteis.ConectaBD;

public class GeraIdRegistro {

	public String geraId() {
		String codigoFormatado = null;

		boolean confere = true;
		int id = 0;
		while (confere) {
			id += 1;
			codigoFormatado = this.formataID(id);
			String sql = "SELECT * FROM registro_de_visitas;";

			try {
				int x = 0;
				PreparedStatement ps = ConectaBD.conexao().prepareStatement(
						sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					x++;
				}
				if (x == 0) {
					confere = false;
				} else {
					rs.beforeFirst();
					while (rs.next()) {
						String confereId = rs.getString("id");
						if (confereId.equals(codigoFormatado)) {
							confere = true;
							break;
						} else {
							confere = false;
						}
					}
				}

				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return codigoFormatado;
	}

	public String formataID(int cod) {
		String codigo = null;
		boolean confere = true;

		while (confere) {
			codigo = String.format("%06d", cod);
			confere = false;
		}
		return codigo;
	}
}
