package local;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_cliente_manual.Gerenciador;

public class ListarLocais {
	ConectaBD con = new ConectaBD();
	Local local = new Local();
	String lista;
	List<String> listLugar = new ArrayList<>();

	public List<String> getListLugar() {
		return listLugar;
	}

	public void setListLugar(List<String> listLugar) {
		this.listLugar = listLugar;
	}

	public void listarLocais(Gerenciador msg) {
		lista = "";
		msg.enviaMensagem("Lista: locais");
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				local.setLugar(rs.getString("lugar"));
				lista += local.toString();
			}
			msg.enviaMensagem(lista);
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> listarLocais() {
		List<String> listLocal = new ArrayList<>();
		String sql = "SELECT * FROM local";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				local.setLugar(rs.getString("lugar"));
				listLugar.add(local.getLugar());
				listLocal.add(local.toString());
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listLocal;

	}

}
