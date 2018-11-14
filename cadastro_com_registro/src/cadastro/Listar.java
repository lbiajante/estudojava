package cadastro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_cliente_manual.Gerenciador;
import registro.ListarRegistro;

public class Listar {
	ListarRegistro listarRegistro = new ListarRegistro();
	ConectaBD con = new ConectaBD();
	CadastroPessoa cad = new CadastroPessoa();
	String lista;
	List<String> listId = new ArrayList<>();
	List<String> listNome = new ArrayList<>();
	List<String> listCpf = new ArrayList<>();
	List<String> listCel = new ArrayList<>();
	List<String> listEmp = new ArrayList<>();
	List<String> listArea = new ArrayList<>();

	public ListarRegistro getListarRegistro() {
		return listarRegistro;
	}

	public void setListarRegistro(ListarRegistro listarRegistro) {
		this.listarRegistro = listarRegistro;
	}

	public List<String> getListId() {
		return listId;
	}

	public void setListId(List<String> listId) {
		this.listId = listId;
	}

	public List<String> getListNome() {
		return listNome;
	}

	public void setListNome(List<String> listNome) {
		this.listNome = listNome;
	}

	public List<String> getListCpf() {
		return listCpf;
	}

	public void setListCpf(List<String> listCpf) {
		this.listCpf = listCpf;
	}

	public List<String> getListCel() {
		return listCel;
	}

	public void setListCel(List<String> listCel) {
		this.listCel = listCel;
	}

	public List<String> getListEmp() {
		return listEmp;
	}

	public void setListEmp(List<String> listEmp) {
		this.listEmp = listEmp;
	}

	public List<String> getListArea() {
		return listArea;
	}

	public void setListArea(List<String> listArea) {
		this.listArea = listArea;
	}

	public void listarCadastros(Gerenciador msg) {

		msg.enviaMensagem("Lista: cadastros de pessoas");
		String sql = "SELECT * FROM cadastro_de_pessoas";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();
			lista = "\n";
			while (rs.next()) {
				cad.setPosicao(rs.getString("id"));
				cad.setNome(rs.getString("nome_pessoa"));
				cad.setDataNascimento(rs.getString("data_nasc"));
				cad.setCpf(rs.getString("cpf"));
				cad.setCelular(rs.getString("celular"));
				cad.setEmpresa(rs.getString("empresa"));
				cad.setAreaDeAtuacao(rs.getString("area_atuacao"));
				lista += cad.toString();

			}
			ps.close();
			msg.enviaMensagem(lista);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> listarCadastros() {
		List<String> listCad = new ArrayList<>();
		String sql = "SELECT * FROM cadastro_de_pessoas";
		try {
			PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
					sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cad.setPosicao(rs.getString("id"));
				cad.setNome(rs.getString("nome_pessoa"));
				cad.setDataNascimento(rs.getString("data_nasc"));
				cad.setCpf(rs.getString("cpf"));
				cad.setCelular(rs.getString("celular"));
				cad.setEmpresa(rs.getString("empresa"));
				cad.setAreaDeAtuacao(rs.getString("area_atuacao"));
				listId.add(cad.getPosicao());
				listNome.add(cad.getNome());
				listCpf.add(cad.getCpf());
				listCel.add(cad.getCelular());
				listEmp.add(cad.getEmpresa());
				listArea.add(cad.getAreaDeAtuacao());
				listCad.add(cad.toString());
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCad;
	}
}
