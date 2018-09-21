package registro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import cadastro.Conexao;
import cadastro.ValidaStrings;

public class CadastrarRegistro {

	Conexao con = new Conexao();
	RegistroVisita reg = new RegistroVisita();
	ValidaDataRegistro data = new ValidaDataRegistro();
	ValidaStrings string = new ValidaStrings();
	ValidaIdRegistro validaId = new ValidaIdRegistro();
	Scanner entrada = new Scanner(System.in);

	public void cadastrar(String IdPessoa, String nomePessoa) {

		System.out.println("Cadastro de Registro");

		IdRegistro id = new IdRegistro(
				validaId.verificaID(textInput("Digite o ID do local visitado")));
		reg.setPosicao(id.getId());
		String label = "Digite o local";
		reg.setLocal(string.texto(textInput(label), label));
		reg.setData(data.data());
		reg.setHora(data.hora());
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
				try {
					PreparedStatement ps = con.conexao().prepareStatement(sql);
					System.out.println(sql);
					ps.execute();

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
