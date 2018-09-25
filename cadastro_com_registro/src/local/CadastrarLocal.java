package local;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaStrings;

public class CadastrarLocal {
	Conexao con = new Conexao();
	Local cadLocal = new Local();
	ValidaStrings string = new ValidaStrings();
	String lugar = null;
	
	Scanner entrada = new Scanner(System.in);

	public String cadastrarLocal() {

		System.out.println("Cadastro de lugares");
		boolean existe = true;

		String label = "Digite o lugar";
		lugar = (string.texto(textInput(label), label));

		String sql = "SELECT * FROM local";

		try {
			PreparedStatement ps = Conexao.conexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
				sql = "INSERT INTO local " + "(lugar) values" + "( '" + lugar.trim()
						+ "' );";
				ps = Conexao.conexao().prepareStatement(sql);
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

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
