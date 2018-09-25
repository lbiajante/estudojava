package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import local.CadastrarLocal;
import local.Local;
import utilitarias.Conexao;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;
import cadastro.CadastroPessoa;

public class CadastrarRegistro {

	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();
	CadastrarLocal cadLocal = new CadastrarLocal();
	Local local = new Local();
	RegistroVisita reg = new RegistroVisita();
	ValidaData data = new ValidaData();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);
	String input;

	public void inserirRegistro() {
		boolean conf = true;
		boolean existe = true;

		while (conf) {
			input = textInput("Digite o ID do cadastro da pessoa para incluir novo registro ou 's' para sair");
			if (input.trim().equalsIgnoreCase("s")) {
				conf = false;
			} else {
				input = validaId.confereID(input);
				String sql = "SELECT * FROM cadastro_de_pessoas";

				try {
					PreparedStatement ps = Conexao.conexao().prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						cad.setNome(rs.getString("nome_pessoa"));
						if (cad.getPosicao().equals(input)) {
							existe = false;
							conf = false;
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

	public void cadastrar(String IdPessoa, String nomePessoa) {

		System.out.println("Cadastro de Registro");
		String table = "registro_de_visitas";
		String label = "Digite o ID do registro de visitas";

		reg.setPosicao(validaId.verificaID(textInput(label), table));		
		reg.setLocal(cadLocal.cadastrarLocal());
		reg.setData(data
				.data("Digite a data da visita com o formato: ddmmaaaa"));
		reg.setHora(data.hora("Digite a hora da visita com o formato: hhmm"));
		reg.setIDpessoa(IdPessoa);
		reg.setNomePessoa(nomePessoa);
		String cadastrar = textInput("Adicionar Registro (S/N)?");
		boolean confere = true;
		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {
				String sql = "INSERT INTO registro_de_visitas "
						+ "(id, visitante, data_visita, hora_visita, id_pessoa, lugar) values"
						+ "( '" + reg.getPosicao() + "' , '"
						+ reg.getNomePessoa() + "' , '" + reg.getData()
						+ "' , '" + reg.getHora() + "' , '" + reg.getIDpessoa()
						+ "' , '" + reg.getLocal().trim() + "' );";
				try {
					PreparedStatement ps = Conexao.conexao().prepareStatement(sql);

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

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
