package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cadastro.Conexao;

public class ListarRegistro {

	Conexao con = new Conexao();
	RegistroVisita reg = new RegistroVisita();

	public void listarRegistros() {

		String sql = "SELECT * FROM registro_de_visitas";
		try {
			PreparedStatement ps = con.conexao().prepareStatement(sql);
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
