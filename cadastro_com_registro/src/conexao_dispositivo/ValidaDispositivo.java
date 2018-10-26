package conexao_dispositivo;

import gerais.ConectaBD;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao_cliente_manual.Gerenciador;

public class ValidaDispositivo {
	RegistroDispositivo crd = new RegistroDispositivo();
	Gerenciador msg;

	public void validaDispositivo(Socket cliente) {

		String nome = null;
		msg = new Gerenciador(cliente);
		msg.enviaMensagem("::PRONTO");
		String recebeDispositivo = msg.recebeMensagem();
		String id = "";
		String sql = "SELECT * FROM cadastro_de_pessoas WHERE id = '"
				+ recebeDispositivo.toString() + "';";
		try (PreparedStatement ps = ConectaBD.getConnection().prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = ps.executeQuery()) {
			int x = 0;
			while (rs.next()) {
				x++;
			}
			if (x == 0) {
				System.out
						.println("Usuario inexistente no cadasatro. Conexao encerrada");
				msg.enviaMensagem("::ERRO");
			} else {

				rs.beforeFirst();
				while (rs.next()) {
					id = rs.getString("id");
					nome = rs.getString("nome_pessoa");
					msg.enviaMensagem("::OK");
					System.out
							.println("Usuario de conexao_dispositivo cadastrado\nID: "
									+ id + " Nome: " + nome + "\n\n");
					break;
				}
				rs.close();
				ps.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		crd.recebeArquivoDispositivo(cliente, msg, id, nome);
		crd.cadastrarRegistroDispositivo(id, nome);
	}
}
