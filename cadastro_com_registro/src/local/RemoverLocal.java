package local;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import conexao_cliente.MensagemObj;
import conexao_cliente.Referencia;
import uteis.ConectaBD;
import uteis.ValidaStrings;

public class RemoverLocal {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	
	public String removerLocal(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
		ValidaStrings string = new ValidaStrings();
		Local local = new Local();
		boolean existe = false;
		boolean confere = true;
		String inputz = null;
		String retorno = null;

		while (confere) {
			
			String label = "Digite um lugar para ser removido ou 's' para sair";
			
			MensagemObj envioCliente1 = new MensagemObj(Referencia.REMOVER,	label);			
			output.writeObject(envioCliente1);			
			MensagemObj recebeCliente1 = (MensagemObj) input.readObject();
			inputz = recebeCliente1.getMensagem();
						
			//inputz = (string.texto(textInput(label), label));
			if (inputz.trim().equalsIgnoreCase("s")) {
				confere = false;
				retorno = "Retorna ao menu principal";
			} else {
				// SQL de listagem
				String sql = "SELECT * FROM local";
				try { // conexão com o BD
					PreparedStatement ps = ConectaBD.conexao().prepareStatement(
							sql);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						// laço que verifica na tabela se o item existe
						local.setLugar(rs.getString("lugar"));
						if (local.getLugar().equals(inputz)) {
							existe = true;
							break;
						}
					}
					ps.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// rotina de exclusão do item validado
				if (existe) {
					String sql2 = "DELETE FROM local WHERE lugar = '" + inputz
							+ "';";
					try {// nova conexão com o BD
						PreparedStatement ps2 = ConectaBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();
						retorno = "Local removido";

					} catch (SQLException e) {
						// O local que está vinculado ao registro de visitas por
						// foreign key e lança SQLException se tentar se excluir
						retorno = "Local nao pode ser removido "
										+ "\nporque está vinculado a uma ou mais visitas";
					}
					confere = false;

				} else if (existe == false) {
					retorno = ("O local indicado não existe para ser removido");
					confere = true;
				}
			}
		}
		return retorno;
	}

}
