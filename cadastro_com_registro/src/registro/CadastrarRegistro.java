package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente.GerenciadorDeClientes;
import uteis.ConectaBD;
import uteis.ValidaData;
import uteis.ValidaId;
import uteis.ValidaStrings;
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
	String input;

	public void inserirRegistro(GerenciadorDeClientes msg) {
		boolean conf = true;
		boolean existe = true;

		while (conf) {
			msg.enviaMensagem("Digite o ID do cadastro da pessoa para incluir novo registro ou 's' para sair");
			input = msg.recebeMensagem();
			if (input.trim().equalsIgnoreCase("s")) {
				conf = false;
			} else {
				input = validaId.confereID(input, msg);
				String sql = "SELECT * FROM cadastro_de_pessoas";

				try {
					PreparedStatement ps = ConectaBD.conexao()
							.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						cad.setPosicao(rs.getString("id"));
						cad.setNome(rs.getString("nome_pessoa"));
						if (cad.getPosicao().equals(input)) {
							existe = false;
							conf = false;
							this.cadastrar(cad.getPosicao(), cad.getNome(), msg);
							break;
						} else {
							conf = true;
						}
					}
					if (existe == true) {
						msg.enviaMensagem("Cadastro não encontrado");
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

	public void cadastrar(String IdPessoa, String nomePessoa,
			GerenciadorDeClientes msg) {

		msg.enviaMensagem("Cadastro de Registro");
		String table = "registro_de_visitas";

		String labelOut = "Digite o ID do registro de visitas";
		msg.enviaMensagem(labelOut);
		String labelIn = msg.recebeMensagem();
		reg.setPosicao(validaId.verificaID(labelIn, table, msg));

		reg.setLocal(cadLocal.cadastrarLocal(msg));

		labelOut = "Digite a data da visita com o formato: ddmmaaaa";
		reg.setData(data.data(labelOut, msg));

		labelOut = "Digite a hora da visita com o formato: hhmm";
		reg.setHora(data.hora(labelOut, msg));

		reg.setIDpessoa(IdPessoa);
		reg.setNomePessoa(nomePessoa);

		msg.enviaMensagem("Adicionar Registro (S/N)?");
		String cadastrar = msg.recebeMensagem();

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
					PreparedStatement ps = ConectaBD.conexao()
							.prepareStatement(sql);
					ps.execute();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				msg.enviaMensagem("Registro adicionado!");
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				msg.enviaMensagem("Registro ignorado!");
				confere = false;
			} else {
				msg.enviaMensagem("Opcao invalida");
				msg.enviaMensagem("Digite uma opcao valida. (S/N)");
				cadastrar = msg.recebeMensagem();
				confere = true;
			}
		}
	}
}
