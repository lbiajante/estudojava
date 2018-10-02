package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilitarias.ConexaoBD;

public class ListarRegistro {

	ConexaoBD con = new ConexaoBD();
	RegistroVisita reg = new RegistroVisita();

	public void listarRegistros() {

		System.out.println("Lista: registro de visitas");
		//SQL delistagem dos itens da tabela registros de visitas
		String sql = "SELECT * FROM registro_de_visitas";
		try {
			PreparedStatement ps = ConexaoBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				reg.setPosicao(rs.getString("id"));
				reg.setNomePessoa(rs.getString("visitante"));
				reg.setData(rs.getString("data_visita"));
				reg.setHora(rs.getString("hora_visita"));
				reg.setIDpessoa(rs.getString("id_pessoa"));
				reg.setLocal(rs.getString("lugar"));

				System.out.println(reg.toString());
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
