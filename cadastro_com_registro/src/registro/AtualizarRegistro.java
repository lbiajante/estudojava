package registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import local.CadastrarLocal;
import uteis.ConectaBD;
import uteis.ValidaData;
import uteis.ValidaId;
import conexao_cliente.Gerenciador;

public class AtualizarRegistro {
	
	public void atualizarRegistro(Gerenciador msg) {

		RegistroVisita regg = new RegistroVisita();
		CadastrarLocal cadLocal = new CadastrarLocal();
		ValidaId validaId = new ValidaId();
		ValidaData data = new ValidaData();
		boolean confere = true;
		boolean existe = false;

		String codigo = null;

		msg.enviaMensagem("Atualizar registros");
		
		while (confere) {
			msg.enviaMensagem("Digite um ID para ser atualizado ou 's' para sair");
			codigo = msg.recebeMensagem();
			
			if (codigo.trim().equalsIgnoreCase("s")) {
				confere = false;
			} else {				
				codigo = validaId.confereID(codigo, msg);
				
				String sql = "SELECT * FROM registro_de_visitas";
				try {
					PreparedStatement ps = ConectaBD.conexao().prepareStatement(
							sql);
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
					regg.setLocal(cadLocal.cadastrarLocal(msg));
					String labelOut = "Atualize a data da visita com o formato: ddmmaaaa";
					regg.setData(data.data(labelOut, msg));
					
					labelOut = "Atualize a hora da visita com o formato: hhmm";
					regg.setHora(data.hora(labelOut, msg));
					
					String sql2 = "UPDATE registro_de_visitas SET data_visita = '"
							+ regg.getData()
							+ "' , hora_visita = '"
							+ regg.getHora()
							+ "' , lugar = '"
							+ regg.getLocal()
							+ "' WHERE id = '"
							+ codigo
							+ "';";
					try { 
						PreparedStatement ps2 = ConectaBD.conexao()
								.prepareStatement(sql2);
						ps2.execute();
						ps2.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
					confere = false;

				} else if (existe == false) {
					msg.enviaMensagem("Nao existe cadastro com esse ID para ser atualizado");
					confere = true;
				}
			}
		}
	}

}
