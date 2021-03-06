package cadastro;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;
import registro.CadastrarRegistro;
import utilitarias_cadastro_manual.ValidaCPF;
import utilitarias_cadastro_manual.ValidaCelular;
import utilitarias_cadastro_manual.ValidaData;
import utilitarias_cadastro_manual.ValidaId;
import utilitarias_cadastro_manual.ValidaStrings;

public class Cadastrar {

	CadastroPessoa cad = new CadastroPessoa();
	CadastrarRegistro cadReg = new CadastrarRegistro();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();

	public String cadastrar(String id, String nome, String cpf, String cel,
			String data, String emp, String area) {
		String retorno = null;
		
		cad.setPosicao(id);
		cad.setNome(nome);
		cad.setCpf(cpf);
		cad.setDataNascimento(this.data.data(data));
		cad.setEmpresa(emp);
		cad.setAreaDeAtuacao(area);

		if (cel.equals("") || cel.equals(null)) {
			cel = "Celular nao informado";
		}
		cad.setCelular(cel);
		
		String sql = "INSERT INTO cadastro_de_pessoas "
				+ "(id, nome_pessoa, data_nasc, cpf, celular, empresa, area_atuacao) values"
				+ "( '" + cad.getPosicao() + "' , '" + cad.getNome()
				+ "' , '" + cad.getDataNascimento() + "' , '"
				+ cad.getCpf() + "' , '" + cad.getCelular() + "' , '"
				+ cad.getEmpresa() + "' , '" + cad.getAreaDeAtuacao()
				+ "' );";
		try {
			PreparedStatement ps = ConectaBD.getConnection()
					.prepareStatement(sql);
			ps.execute();
			ps.close();
			retorno = "Cadastro efetuado com sucesso";
			
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = "Cadastro não efetuado";
		}
		
return retorno;
	}

	public void cadastrar(Gerenciador msg) {
		String labelOut = "Cadastro de Usuario";
		msg.enviaMensagem(labelOut);
		String table = "cadastro_de_pessoas";

		labelOut = "Digite o ID a ser cadastrado";
		msg.enviaMensagem(labelOut);
		String labelIn = msg.recebeMensagem();
		cad.setPosicao(validaId.verificaID(labelIn, table, msg));

		labelOut = "Digite o nome";
		msg.enviaMensagem(labelOut);
		labelIn = msg.recebeMensagem();
		cad.setNome(string.texto(labelIn, labelOut, msg));

		labelOut = "Digite a data de nascimento com o formato: ddmmaaaa";
		cad.setDataNascimento(data.data(labelOut, msg));

		cad.setCpf(cpf.validarCPF(msg));

		labelOut = "Digite o nome da empresa";
		msg.enviaMensagem(labelOut);
		labelIn = msg.recebeMensagem();
		cad.setEmpresa(string.texto(labelIn, labelOut, msg));

		labelOut = "Digite a area de atuacao";
		msg.enviaMensagem(labelOut);
		labelIn = msg.recebeMensagem();
		cad.setAreaDeAtuacao(string.texto(labelIn, labelOut, msg));

		cad.setCelular(celular.formatarCelular(msg));

		msg.enviaMensagem("Adicionar cadastro (S/N)?");
		labelIn = msg.recebeMensagem();
		boolean confere = true;
		while (confere) {
			if (labelIn.trim().equalsIgnoreCase("s")) {

				String sql = "INSERT INTO cadastro_de_pessoas "
						+ "(id, nome_pessoa, data_nasc, cpf, celular, empresa, area_atuacao) values"
						+ "( '" + cad.getPosicao() + "' , '" + cad.getNome()
						+ "' , '" + cad.getDataNascimento() + "' , '"
						+ cad.getCpf() + "' , '" + cad.getCelular() + "' , '"
						+ cad.getEmpresa() + "' , '" + cad.getAreaDeAtuacao()
						+ "' );";
				try {
					PreparedStatement ps = ConectaBD.getConnection()
							.prepareStatement(sql);
					ps.execute();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				msg.enviaMensagem("Cadastro adicionado!");
				msg.enviaMensagem(cad.toString());
				confere = false;
				boolean conf = true;
				do {
					msg.enviaMensagem("Deseja registrar uma visita? (S/N)");
					String opcaoVisita = null;
					opcaoVisita = msg.recebeMensagem();

					if (opcaoVisita.trim().equalsIgnoreCase("s")) {
						cadReg.cadastrar(cad.getPosicao(), cad.getNome(), msg);
						conf = true;

					} else if (opcaoVisita.equalsIgnoreCase("n")) {
						conf = false;
					} else {
						msg.enviaMensagem("Opcao invalida! Tente novamente!");
						conf = true;
					}
				} while (conf);

			} else if (labelIn.trim().equalsIgnoreCase("n")) {
				msg.enviaMensagem("Cadastro ignorado!");
				confere = false;

			} else {
				msg.enviaMensagem("Opcao invalida");
				msg.enviaMensagem("Digite uma opcao valida. (S/N)");
				labelIn = msg.recebeMensagem();
				confere = true;
			}
		}
	}

}
