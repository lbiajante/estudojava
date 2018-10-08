package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import registro.ListarRegistro;
import uteis.ConectaBD;

public class Listar {
	ListarRegistro listarRegistro = new ListarRegistro();
	ConectaBD con = new ConectaBD();
	CadastroPessoa cad = new CadastroPessoa();
	String lista ;
	public String listarCadastros() {

		lista = "";
		System.out.println("Lista: cadastros de pessoas");
		// SQL de listagem completa dos cadastros de pessoas
		String sql = "SELECT * FROM cadastro_de_pessoas";
		try {
			PreparedStatement ps = ConectaBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// laço para a apresentação de todos os elementos da tabela cadastro
			// de pessoas do BD
			while (rs.next()) {

				cad.setPosicao(rs.getString("id"));
				cad.setNome(rs.getString("nome_pessoa"));
				cad.setDataNascimento(rs.getString("data_nasc"));
				cad.setCpf(rs.getString("cpf"));
				cad.setCelular(rs.getString("celular"));
				cad.setEmpresa(rs.getString("empresa"));
				cad.setAreaDeAtuacao(rs.getString("area_atuação"));
				
				lista += cad.toString();				
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
}
