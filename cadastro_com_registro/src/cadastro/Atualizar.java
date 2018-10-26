package cadastro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilitarias_cadastro_manual.ValidaCPF;
import utilitarias_cadastro_manual.ValidaCelular;
import utilitarias_cadastro_manual.ValidaData;
import utilitarias_cadastro_manual.ValidaId;
import utilitarias_cadastro_manual.ValidaStrings;
import conexao_cliente_manual.Gerenciador;

public class Atualizar {

	public void atualizarCadastro(Gerenciador msg) {

		CadastroPessoa cad = new CadastroPessoa();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		boolean confere = true;
		boolean existe = false;
		String codigo = null;

		msg.enviaMensagem("Atualizar: cadastro de pessoas ");

		while (confere) {
			msg.enviaMensagem("Digite um ID para ser atualizado ou 's' para sair");
			codigo = msg.recebeMensagem();
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {
				codigo = validaId.confereID(codigo, msg);
				String sql = "SELECT * FROM cadastro_de_pessoas";
				try {
					PreparedStatement ps = ConectaBD.getConnection()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						if (cad.getPosicao().equals(codigo)) {
							existe = true;
							break;
						}
					}
					rs.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (existe) {
					String labelOut = "Digite o nome atualizado";
					msg.enviaMensagem(labelOut);
					String labelIn = msg.recebeMensagem();
					cad.setNome(string.texto(labelIn, labelOut, msg));

					labelOut = "Digite a data de nascimento com o formato: ddmmaaaa";
					cad.setDataNascimento(data.data(labelOut, msg));

					cad.setCpf(cpf.validarCPF(msg));
					labelOut = "Digite o nome da empresa atualizado";
					msg.enviaMensagem(labelOut);
					labelIn = msg.recebeMensagem();
					cad.setEmpresa(string.texto(labelIn, labelOut, msg));

					labelOut = "Digite a area de atuacao";
					msg.enviaMensagem(labelOut);
					labelIn = msg.recebeMensagem();
					cad.setAreaDeAtuacao(string.texto(labelIn, labelOut, msg));

					cad.setCelular(celular.formatarCelular(msg));

					String sql2 = "UPDATE cadastro_de_pessoas SET nome_pessoa = '"
							+ cad.getNome()
							+ "' , data_nasc = '"
							+ cad.getDataNascimento()
							+ "' , cpf = '"
							+ cad.getCpf()
							+ "' , celular = '"
							+ cad.getCelular()
							+ "' , empresa = '"
							+ cad.getEmpresa()
							+ "' , area_atuacao = '"
							+ cad.getAreaDeAtuacao()
							+ "' WHERE id = '"
							+ codigo + "';";
					try {
						PreparedStatement ps2 = ConectaBD.getConnection()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (existe == false) {
					msg.enviaMensagem("Nao existe cadastro com esse ID para ser atualizado");
					confere = true;
				}
			}
		}
	}
}
