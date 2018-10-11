package uteis;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conexao_cliente.Gerenciador;
import local.Local;
import cadastro.CadastroPessoa;
import registro.RegistroVisita;

public class ValidaId {

	CadastroPessoa cad = new CadastroPessoa();
	RegistroVisita reg = new RegistroVisita();
	Local local = new Local();
	Scanner entrada = new Scanner(System.in);

	public String confereID(String codigo, Gerenciador msg) {
		int cod = 0;
		boolean confere = true;
		do {
			try {
				while (confere) {
					cod = Integer.parseInt(codigo.trim());

					if (cod <= 0) {
						msg.enviaMensagem("O codigo precisa ser maior que zero");
						msg.enviaMensagem("Digite um numero maior que zero");
						codigo = msg.recebeMensagem();
						confere = true;
						cod = 0;
					} else {
						codigo = String.format("%06d", cod).trim();
						confere = false;
					}
				}
			} catch (NumberFormatException e) {
				msg.enviaMensagem("Voce nao digitou um numero inteiro!\n");
				msg.enviaMensagem("Digite um numero inteiro");
				codigo = msg.recebeMensagem();
				cod = 0;
			}
		} while (cod == 0);
		return codigo;
	}

	public String verificaID(String codigo, String table, Gerenciador msg) {
		boolean confere = true;
		String confereCod = null;
		while (confere) {
			confereCod = this.confereID(codigo, msg);
			String sql = "SELECT * FROM " + table + ";";

			try {
				PreparedStatement ps = ConectaBD.conexao()
						.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				if (table.equals("cadastro_de_pessoas")) {

					if (rs.next() == true) {
						while (rs.next()) {
							cad.setPosicao(rs.getString("id"));
							if (cad.getPosicao().equals(confereCod)) {
								msg.enviaMensagem("Esse ID esta sendo usado, por favor digite outro");
								codigo = msg.recebeMensagem();
								rs.close();
								ps.close();
								confere = true;
								break;
							} else {
								confere = false;
								break;
							}
						}
					} else {
						break;
					}
				} else if (table.equals("registro_de_visitas")) {
					if (rs.next() == true) {
						while (rs.next()) {
							reg.setPosicao(rs.getString("id"));
							if (reg.getPosicao().equals(confereCod)) {
								msg.enviaMensagem("Esse ID esta sendo usado, por favor digite outro");
								codigo = msg.recebeMensagem();
								rs.close();
								ps.close();
								break;
							} else {
								confere = false;
							}
						}
					} else {
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confereCod;
	}
}
