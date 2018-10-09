package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente.GerenciadorDeClientes;
import registro.ListarRegistro;
import uteis.ConectaBD;

public class Listar {
	ListarRegistro listarRegistro = new ListarRegistro();
	ConectaBD con = new ConectaBD();
	CadastroPessoa cad = new CadastroPessoa();
	String lista ;
	public void listarCadastros(GerenciadorDeClientes msg) {
		
		msg.enviaMensagem("Lista: cadastros de pessoas");
		
		String sql = "SELECT * FROM cadastro_de_pessoas";
		try {
			PreparedStatement ps = ConectaBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			lista = "\n";
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
			msg.enviaMensagem(lista);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
