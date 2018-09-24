package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import registro.ListarRegistro;
import utilitarias.Conexao;

public class Listar {
	ListarRegistro listarRegistro = new ListarRegistro();
	private Scanner entrada;
	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();

	public void listarCadastros() {

		System.out.println("Lista: cadastros de pessoas");

		String sql = "SELECT * FROM cadastro_de_pessoas";
		try {
			PreparedStatement ps = con.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				cad.setPosicao(rs.getString("id"));
				cad.setNome(rs.getString("nome_pessoa"));
				cad.setDataNascimento(rs.getString("data_nasc"));
				cad.setCpf(rs.getString("cpf"));
				cad.setCelular(rs.getString("celular"));
				cad.setEmpresa(rs.getString("empresa"));
				cad.setAreaDeAtuacao(rs.getString("area_atuação"));

				System.out.println(cad.toString());
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
