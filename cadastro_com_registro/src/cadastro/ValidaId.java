package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ValidaId {

	Conexao con = new Conexao();
	CadastroPessoa cad = new CadastroPessoa();
	Scanner entrada = new Scanner(System.in);

	public int verificaID(String codigo) {

		int cod = 0;
		boolean confere = true;
		String confereCod = null;

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
						confereCod = String.format("%06d", cod).trim();		
						
						String sql = "SELECT * FROM cadastro_de_pessoas";
						try {
							PreparedStatement ps = con.conexao().prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							
							while(rs.next()){							    
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
							
						} catch (SQLException e) {
							e.printStackTrace();
						}				
				
					}
				}
			} catch (NumberFormatException e) {
				System.out.printf("Voce nao digitou um numero inteiro!\n");
				codigo = textInput("Digite um numero inteiro");
				cod = 0;
			}
		} while (cod == 0);
		return cod;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

}
