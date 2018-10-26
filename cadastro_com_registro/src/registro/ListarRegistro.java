package registro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;

public class ListarRegistro {

	ConectaBD con = new ConectaBD();
	RegistroVisita reg = new RegistroVisita();
	String lista;

	public void listarRegistros(Gerenciador msg) {

		lista = "";
		msg.enviaMensagem("Lista: registro de visitas");
		// SQL delistagem dos itens da tabela registros de visitas
		String sql = "SELECT * FROM registro_de_visitas";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				reg.setPosicao(rs.getString("id"));
				reg.setNomePessoa(rs.getString("visitante"));
				reg.setData(rs.getString("data_visita"));
				reg.setHora(rs.getString("hora_visita"));
				reg.setIDpessoa(rs.getString("id_pessoa"));
				reg.setLocal(rs.getString("lugar"));

				lista += reg.toString();
			}
			msg.enviaMensagem(lista);
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
