package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import uteis.Conexao;
import uteis.ValidaCPF;
import uteis.ValidaCelular;
import uteis.ValidaData;
import uteis.ValidaId;
import uteis.ValidaStrings;

public class Atualizar {
	Scanner entrada = new Scanner(System.in);

	//método para impressão em tela e captura de entrada de dados do usuário
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarCadastro() {
		
		CadastroPessoa cad = new CadastroPessoa();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		boolean confere = true;
		boolean existe = false;
		String codigo = null;

		System.out.println("Atualizar: cadastro de pessoas ");

		while (confere) { //laço para garantir que a entrada do código será válida
			codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
			if (codigo.trim().equalsIgnoreCase("s")) { //opção de sair sem a obrigação de finalizar a rotina de atualização inteira 
				confere = false;
			} else {
				codigo = validaId.confereID(codigo); //método de validação de ID
				String sql = "SELECT * FROM cadastro_de_pessoas"; //SQL para verificar se o cadastro a ser atualizado está no BD
				try {
					PreparedStatement ps = Conexao.conexao().prepareStatement(
							sql); //abertura de conexão com o BD
					ResultSet rs = ps.executeQuery(); 
					while (rs.next()) { //laço de procura no BD
						cad.setPosicao(rs.getString("id")); 
						if (cad.getPosicao().equals(codigo)) {
							existe = true;  //marcador de existência do cadastro no BD
							break;
						}
					}
					rs.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				if (existe) { //rotina de atualização com solicitação de novos valores para os atributos
					String label = "Digite o nome atualizado";
					cad.setNome(string.texto(textInput(label), label));
					cad.setDataNascimento(data
							.data("Digite a data de nascimento com o formato: ddmmaaaa"));
					cad.setCpf(cpf.validarCPF());
					label = "Digite o nome da empresa atualizado";
					cad.setEmpresa(string.texto(textInput(label), label));
					label = "Digite a area de atuacao";
					cad.setAreaDeAtuacao(string.texto(textInput(label), label));
					cad.setCelular(celular.formatarCelular());

					String sql2 = "UPDATE cadastro_de_pessoas SET nome_pessoa = '" //SQL de atualização dos atributos no BD
							+ cad.getNome()
							+ "' , data_nasc = '"
							+ cad.getDataNascimento()
							+ "' , cpf = '"
							+ cad.getCpf()
							+ "' , celular = '"
							+ cad.getCelular()
							+ "' , empresa = '"
							+ cad.getEmpresa()
							+ "' , area_atuação = '"
							+ cad.getAreaDeAtuacao()
							+ "' WHERE id = '"
							+ codigo + "';";
					try {
						PreparedStatement ps2 = Conexao.conexao()
								.prepareStatement(sql2); //nova conexão com o BD para atualizar
						ps2.execute();
						ps2.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (existe == false) {
					System.out
							.println("Nao existe cadastro com esse ID para ser atualizado");
					confere = true;
				}
			}
		}
	}
}
