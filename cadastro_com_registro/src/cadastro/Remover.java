package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente.GerenciadorDeClientes;
import uteis.ConectaBD;
import uteis.ValidaId;

public class Remover {

	public void removerCadastro(GerenciadorDeClientes msg) {
		ValidaId validaId = new ValidaId();
		CadastroPessoa cad = new CadastroPessoa();
		String codigo = null;

		msg.enviaMensagem("Remover: cadastro de pessoas");

		boolean confere = true;
		while (confere) {
			msg.enviaMensagem("Digite um ID para ser removido ou 's' para sair");
			codigo = msg.recebeMensagem();
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				codigo = validaId.confereID(codigo, msg);
				String sql = "SELECT * FROM cadastro_de_pessoas;";
				boolean existe = false;
				try {
					PreparedStatement ps = ConectaBD.conexao()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						if (cad.getPosicao().equals(codigo)) {
							existe = true;
							break;
						}
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (existe) {
					String sql2 = "DELETE FROM cadastro_de_pessoas WHERE id = '"
							+ codigo + "';";
					try {
						PreparedStatement ps2 = ConectaBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						msg.enviaMensagem("Cadastro removido");

					} catch (SQLException e) {
						msg.enviaMensagem("Cadastro desta pessoa nao pode ser removido, "
								+ "\npois esta vinculado a um ou mais registros de visitas");
					}
					confere = false;
				} else if (existe == false) {
					msg.enviaMensagem("Nao existe cadastro com esse ID para ser removido");
					confere = true;
				}
			}
		}
	}
}
