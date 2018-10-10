package cadastro;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao_cliente.GerenciadorDeClientes;
import registro.CadastrarRegistro;
import uteis.ConectaBD;
import uteis.ValidaCPF;
import uteis.ValidaCelular;
import uteis.ValidaData;
import uteis.ValidaId;
import uteis.ValidaStrings;

public class Cadastrar {

	CadastroPessoa cad = new CadastroPessoa();
	CadastrarRegistro cadReg = new CadastrarRegistro();
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	

	public void cadastrar(GerenciadorDeClientes msg) {
		String labelOut = "Cadastro de Usuario";
		msg.enviaMensagem(labelOut);
		String table = "cadastro_de_pessoas";
		
		labelOut = "Digite o ID a ser cadastrado";
		msg.enviaMensagem(labelOut);
		String labelIn = msg.recebeMensagem();
		cad.setPosicao(validaId.verificaID(labelIn, table, msg));
		
		labelOut ="Digite o nome";
		msg.enviaMensagem(labelOut);
		labelIn = msg.recebeMensagem();
		cad.setNome(string.texto(labelIn, labelOut , msg));
		
		labelOut ="Digite a data de nascimento com o formato: ddmmaaaa";				
		cad.setDataNascimento(data.data(labelOut, msg));
		
		cad.setCpf(cpf.validarCPF(msg));
		
		labelOut = "Digite o nome da empresa";
		msg.enviaMensagem (labelOut);
		labelIn = msg.recebeMensagem();
		cad.setEmpresa(string.texto(labelIn, labelOut, msg));
		
		labelOut = "Digite a area de atuacao";
		msg.enviaMensagem (labelOut);
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
					PreparedStatement ps = ConectaBD.conexao().prepareStatement(
							sql); 
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
