package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;
import cadastro.CadastroPessoa;

public class CadastrarRegistro {

	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();
	RegistroVisita reg = new RegistroVisita();
	ValidaData data = new ValidaData();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);
	String codigo;

	public void inserirRegistro() {
		boolean conf = true;
		boolean existe = true;
		while (conf) {
			codigo = textInput("Digite o ID do cadastro da pessoa \n para incluir novo registro ou 's' para sair");
			if (codigo.trim().equalsIgnoreCase("s")) {
				conf = false;
			} else {
				codigo = validaId.confereID(codigo);

				String sql = "SELECT * FROM cadastro_de_pessoas";

				try {
					PreparedStatement ps = con.conexao().prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						cad.setNome(rs.getString("nome_pessoa"));
						if (cad.getPosicao().equals(codigo)) {
							this.cadastrar(cad.getPosicao(), cad.getNome());
							existe = false;
							conf = false;
							break;							
						} else {						
							conf = true;							
						}
					}
					if (existe == true){
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
		String label = "Digite o ID do local visitado";
		
		reg.setPosicao(validaId.verificaID(textInput(label), table));
		label = "Digite o local";
		reg.setLocal(string.texto(textInput(label), label));
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
						+ "' , '" + reg.getLocal() + " ' );";
				System.out.println(sql);
				try {
					PreparedStatement ps = con.conexao().prepareStatement(sql);

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
