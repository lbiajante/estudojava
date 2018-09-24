package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Utilitarias.Conexao;
import Utilitarias.ValidaCPF;
import Utilitarias.ValidaCelular;
import Utilitarias.ValidaData;
import Utilitarias.ValidaId;
import Utilitarias.ValidaStrings;
import cadastro.CadastroPessoa;

public class AtualizarRegistro {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarRegistro() {

		RegistroVisita regg = new RegistroVisita();

		Conexao con = new Conexao();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		boolean confere2 = true;
		boolean confere = true;
		boolean existe = false;

		String codigo = null;

		System.out.println("Atualizar registros");

		do {
			try {
				while (confere) {
					codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
					if (codigo.trim().equalsIgnoreCase("s")) {

						confere = false;
						confere2 = false;
					} else {
						codigo = validaId.confereID(codigo);

						String sql = "SELECT * FROM registro_de_visitas";
						try {
							PreparedStatement ps = con.conexao()
									.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while (rs.next()) {
								regg.setPosicao(rs.getString("id"));
								if (regg.getPosicao().equals(codigo)) {
									existe = true;
									break;
								}
							}
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}

						if (existe) {

							String label = "Digite o nome atualizado";
							regg.setNomePessoa(string.texto(textInput(label),
									label));
							regg.setLocal(string.texto(textInput(label), label));
							regg.setData(data
									.data("Digite a data da visita com o formato: ddmmaaaa"));
							regg.setHora(data
									.hora("Digite a hora da visita com o formato: hhmm"));
							label = "Digite o ID da pessoa";
							regg.setIDpessoa(string.texto(textInput(label),
									label));
							label = "Digite o nome da pessoa";
							regg.setNomePessoa(string.texto(textInput(label),
									label));

							String sql2 = "UPDATE registro_de_visitas SET visitante = '"
									+ regg.getNomePessoa()
									+ "' , data_visita = '"
									+ regg.getData()
									+ "' , hora_visita = '"
									+ regg.getHora()
									+ "' , id_pessoa = '"
									+ regg.getIDpessoa()
									+ "' , lugar = '" + regg.getLocal()

									+ "' WHERE id = '" + codigo + "';";
							System.out.println(sql2);
							try {
								PreparedStatement ps2 = con.conexao()
										.prepareStatement(sql2);
								ps2.execute();
								ps2.close();

							} catch (SQLException e) {
								e.printStackTrace();
							}
							confere = false;
							confere2 = false;

						} else if (existe == false) {
							System.out
							.println("Nao existe cadastro com esse ID para ser atualizado");
							confere = true;
							confere2 = true;
						}
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um numero inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				confere2 = true;
			}
		} while (confere2);
	}

}
