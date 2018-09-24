package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import registro.RegistroVisita;

public class ValidaId {

	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();
	RegistroVisita reg = new RegistroVisita();
	Scanner entrada = new Scanner(System.in);

	public String confereID(String codigo) {

		int cod = 0;
		boolean confere = true;

		do {
			try {
				while (confere) {
					cod = Integer.parseInt(codigo.trim());
					if (cod <= 0) {
						System.out
						.println("O codigo precisa ser maior que zero");
						confere = true;
						cod = 0;
					} else {
						codigo = String.format("%06d", cod).trim();
						confere = false;
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um numero inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				cod = 0;
			}
		} while (cod == 0);
		return codigo;
	}

	public String verificaID(String codigo, String table) {	
		int cod = 0;
		boolean confere = true;
		String confereCod = this.confereID(codigo);


		String sql = "SELECT * FROM " + table;
		try {
			PreparedStatement ps = con.conexao()
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (table.equals("cadastro_de_pessoas")) {

				while (rs.next()) {
					cad.setPosicao(rs.getString("id"));
					if (cad.getPosicao().equals(confereCod)) {
						System.out
						.println("Esse ID esta sendo usado, por favor digite outro");
						codigo = entrada.next();
						confere = true;
						break;
					} else {
						confere = false;
					}
				}
			} else if (table.equals("registro_de_visitas")) {
				while (rs.next()) {
					reg.setPosicao(rs.getString("id"));

					if (reg.getPosicao().equals(confereCod)) {
						System.out
						.println("Esse ID esta sendo usado, por favor digite outro");
						codigo = entrada.next();
						confere = true;
						break;

					} else {
						confere = false;
					}
				}
			}
			con.conexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return confereCod;
	}


private String textInput(String label) {
	System.out.println(label);
	return entrada.nextLine();
}

}
