package registro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_cliente_manual.Gerenciador;

public class ListarRegistro {

	ConectaBD con = new ConectaBD();
	RegistroVisita reg = new RegistroVisita();
	String lista;
	List<String> listRegId = new ArrayList<>();
	List<String> listRegVisit = new ArrayList<>();
	List<String> listRegData = new ArrayList<>();
	List<String> listRegHora = new ArrayList<>();
	List<String> listRegIdPessoa = new ArrayList<>();
	List<String> listRegLugar = new ArrayList<>();

	public List<String> getListRegId() {
		return listRegId;
	}
	public void setListRegId(List<String> listRegId) {
		this.listRegId = listRegId;
	}
	public List<String> getListRegVisit() {
		return listRegVisit;
	}
	public void setListRegVisit(List<String> listRegVisit) {
		this.listRegVisit = listRegVisit;
	}
	public List<String> getListRegData() {
		return listRegData;
	}
	public void setListRegData(List<String> listRegData) {
		this.listRegData = listRegData;
	}
	public List<String> getListRegHora() {
		return listRegHora;
	}
	public void setListRegHora(List<String> listRegHora) {
		this.listRegHora = listRegHora;
	}
	public List<String> getListRegIdPessoa() {
		return listRegIdPessoa;
	}
	public void setListRegIdPessoa(List<String> listRegIdPessoa) {
		this.listRegIdPessoa = listRegIdPessoa;
	}
	public List<String> getListRegLugar() {
		return listRegLugar;
	}
	public void setListRegLugar(List<String> listRegLugar) {
		this.listRegLugar = listRegLugar;
	}
	public void listarRegistros(Gerenciador msg) {
		lista = "";
		msg.enviaMensagem("Lista: registro de visitas");		
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
	public List<String> listarRegistros() {
		List<String> listReg = new ArrayList<>();			
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

				listRegId.add(reg.getPosicao());
				listRegData.add(reg.getData());
				listRegHora.add(reg.getHora());
				listRegIdPessoa.add(reg.getIDpessoa());
				listRegVisit.add(reg.getNomePessoa());
				listRegLugar.add(reg.getLocal());
				listReg.add(reg.toString());
			}			
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listReg;
	}
}
