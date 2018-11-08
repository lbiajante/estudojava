package servlet;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import registro.ListarRegistro;
import cadastro.CadastroPessoa;

public class ListaCadastroServlet {

	private ListarRegistro listarRegistro = new ListarRegistro();
	private CadastroPessoa cad = new CadastroPessoa();

	private List<CadastroPessoa> listaCompleta = new ArrayList<>();
	private ArrayList<String> listaId = new ArrayList<>();
	private ArrayList<String> listaNome = new ArrayList<>();
	private ArrayList<String> listaCpf = new ArrayList<>();
	private ArrayList<String> listaData = new ArrayList<>();
	private ArrayList<String> listaCel = new ArrayList<>();
	private ArrayList<String> listaEmpresa = new ArrayList<>();
	private ArrayList<String> listaArea = new ArrayList<>();

	public ListarRegistro getListarRegistro() {
		return listarRegistro;
	}

	public List<CadastroPessoa> getListaCompleta() {
		return listaCompleta;
	}

	public ArrayList<String> getListaId() {
		return listaId;
	}

	public ArrayList<String> getListaNome() {
		return listaNome;
	}

	public ArrayList<String> getListaCpf() {
		return listaCpf;
	}

	public ArrayList<String> getListaData() {
		return listaData;
	}

	public ArrayList<String> getListaCel() {
		return listaCel;
	}

	public ArrayList<String> getListaEmpresa() {
		return listaEmpresa;
	}

	public ArrayList<String> getListaArea() {
		return listaArea;
	}
	
//	public void printNaTela (){
//		System.out.println("print");
//		this.listarCadastros();
//		System.out.println("print 2");
//		for (Object o: listaId){
//			System.out.println(((String) o).toString());
//		}
//	}

	public List<CadastroPessoa> listarCadastros() {
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

				listaId.add(cad.getPosicao());
				listaNome.add(cad.getNome());
				listaData.add(cad.getDataNascimento());
				listaCpf.add(cad.getCpf());
				listaCel.add(cad.getCelular());
				listaEmpresa.add(cad.getEmpresa());
				listaArea.add(cad.getAreaDeAtuacao());

				listaCompleta.add(cad);

			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCompleta;

	}
}
