package conexao_dispositivo;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class GeraIdRegistro {

	public String geraId() {
		String codigoFormatado = null;
		Random ran = new Random();
		boolean confere = true;
		int id = ran.nextInt(999998);
		while (confere) {
			id += 1;
			codigoFormatado = this.formataID(id);
			System.out.println(codigoFormatado + " codigo");			
			String sql = "SELECT * FROM registro_de_visitas where id = '"
					+ codigoFormatado + "';";

			try {
				System.out.println("try");
				int x = 0;
				PreparedStatement ps = ConectaBD.conexao().prepareStatement(
						sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();			
				
				while (rs.next()) {
					x++;
				}
				if (x == 0) {
					System.out.println("nao tem registro");
					confere = false;
					break;
				} else {
					System.out.println("tem registro");
					confere = true;
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				confere = true;
				System.out.println("caiu no catch");
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
