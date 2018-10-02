package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.ConexaoBD;
import utilitarias.ValidaStrings;

public class CadastrarLocal {

	Local cadLocal = new Local();
	ValidaStrings string = new ValidaStrings();
	String lugar = null;
	Scanner entrada = new Scanner(System.in);

	public String cadastrarLocal() {
		System.out.println("Cadastro de lugares");
		boolean existe = true;

		String label = "Digite o lugar";
		lugar = (string.texto(textInput(label), label));
		// SQL de listagem de todos os itens da tabela local
		String sql = "SELECT * FROM local";

		try { // conexão com o BD
			PreparedStatement ps = ConexaoBD.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// laço para a verificação se o local digitado já existe no BD
				cadLocal.setLugar(rs.getString("lugar"));
				if (cadLocal.getLugar().equals(lugar)) {
					existe = false;
					break;
				}
			}
			if (existe == false) {
				System.out.println("Lugar já cadastrado");
				lugar = cadLocal.getLugar();

			} else if (existe == true) {
				System.out.println("Lugar não cadastrado ainda!");
				// novo SQL para inserção de item ainda não cadastrado
				sql = "INSERT INTO local " + "(lugar) values" + "( '"
						+ lugar.trim() + "' );";
				ps = ConexaoBD.conexao().prepareStatement(sql);
				ps.execute();
				System.out.println("Ok! Lugar adicionado!");
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lugar;
	}

	// método para impressão em tela e captura de entrada de dados do usuário
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
