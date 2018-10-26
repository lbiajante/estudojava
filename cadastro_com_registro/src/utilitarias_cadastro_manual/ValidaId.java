package utilitarias_cadastro_manual;

import gerais.ConectaBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import local.Local;
import registro.RegistroVisita;
import cadastro.CadastroPessoa;
import conexao_cliente_manual.Gerenciador;

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
			String sql = "SELECT * FROM cadastro_de_pessoas ;";

			try {
				int x = 0;
				PreparedStatement ps = ConectaBD.getConnection().prepareStatement(
						sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					x++;
				}
				if (x == 0) {
					confere = false;

				} else {
					rs.beforeFirst();
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
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return confereCod;
	}
}