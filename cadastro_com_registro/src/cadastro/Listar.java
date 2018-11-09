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
				listCad.add(cad.toString());
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCad;
	}
}
