package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.ConexaoBD;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;
import local.CadastrarLocal;
import local.Local;
import cadastro.CadastroPessoa;

public class CadastrarRegistro {

	CadastroPessoa cad = new CadastroPessoa();
	CadastrarLocal cadLocal = new CadastrarLocal();
	Local local = new Local();
	RegistroVisita reg = new RegistroVisita();
	ValidaData data = new ValidaData();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);
	String input;

	//método para a inserção de registro avulso
	public void inserirRegistro() {
		boolean conf = true;
		boolean existe = true;
		// laço para inclusão de um ID de cadasto de pessoa válido para a
		// referência na foreign key
		while (conf) {
			input = textInput("Digite o ID do cadastro da pessoa "
					+ "para incluir novo registro ou 's' para sair");
			// opção de sair sem inclusão de novo registro
			if (input.trim().equalsIgnoreCase("s")) {
				conf = false;
			} else {
				// método que valida o ID
				input = validaId.confereID(input);
				// SQL de listagem da tabela cadastro de pessoas para vincular o
				// registro a um cadastro válido
				String sql = "SELECT * FROM cadastro_de_pessoas";

				try { //conexão com o BD
					PreparedStatement ps = ConexaoBD.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					//laço de busca do item indicado
					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						cad.setNome(rs.getString("nome_pessoa"));
						//marcação de existência e coleta do item indicado
						if (cad.getPosicao().equals(input)) {
							existe = false;
							conf = false;
							//método de inserção de registro
							this.cadastrar(cad.getPosicao(), cad.getNome());
							break;
						} else {
							conf = true;
						}
					}
					if (existe == true) {
						System.out.println("Cadastro não encontrado");
						conf = true;
					}
					ps.close();
					rs.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
//método de inserção de registro 
	public void cadastrar(String IdPessoa, String nomePessoa) {

		System.out.println("Cadastro de Registro");
		String table = "registro_de_visitas";
		String label = "Digite o ID do registro de visitas";
//coleta de dados para inserção de item
		reg.setPosicao(validaId.verificaID(textInput(label), table));
		reg.setLocal(cadLocal.cadastrarLocal());
		reg.setData(data
				.data("Digite a data da visita com o formato: ddmmaaaa"));
		reg.setHora(data.hora("Digite a hora da visita com o formato: hhmm"));
		reg.setIDpessoa(IdPessoa);
		reg.setNomePessoa(nomePessoa);
		String cadastrar = textInput("Adicionar Registro (S/N)?");
		boolean confere = true;
//laço para garantir a entrada de uma opção válida
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				//SQL para inserção de registros na tabela registro de visitas
				String sql = "INSERT INTO registro_de_visitas "
						+ "(id, visitante, data_visita, hora_visita, id_pessoa, lugar) values"
						+ "( '" + reg.getPosicao() + "' , '"
						+ reg.getNomePessoa() + "' , '" + reg.getData()
						+ "' , '" + reg.getHora() + "' , '" + reg.getIDpessoa()
						+ "' , '" + reg.getLocal().trim() + "' );";
				try {//conexão com o BD
					PreparedStatement ps = ConexaoBD.conexao().prepareStatement(
							sql);
					ps.execute();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Registro adicionado!");
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				System.out.println("Registro ignorado!");
				confere = false;
			} else {
				System.out.println("Opcao invalida");
				cadastrar = textInput("Digite uma opcao valida. (S/N)");
				confere = true;
			}
		}
	}
	//método para impressão em tela e captura de entrada de dados do usuário
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
