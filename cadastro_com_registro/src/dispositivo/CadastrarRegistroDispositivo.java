package dispositivo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import local.CadastrarLocal;
import local.Local;
import registro.RegistroVisita;
import uteis.ConectaBD;
import conexao_cliente.Gerenciador;

public class CadastrarRegistroDispositivo {
	private CadastrarLocalDispositivo cadLocal = new CadastrarLocalDispositivo();
	private RegistroVisita reg = new RegistroVisita();
	private GeraIdRegistro idAutomatico = new GeraIdRegistro();

	public void cadastrarRegistroDispositivo (String IdPessoa, String nomePessoa,
			Gerenciador msg) {		
		
		msg.recebeMensagem();
		
		reg.setPosicao(idAutomatico.geraId());
		reg.setLocal(cadLocal.cadastrarLocal(msg, local));	
		reg.setData(data.data(labelOut, msg));
		reg.setHora(data.hora(labelOut, msg));
		reg.setIDpessoa(IdPessoa);
		reg.setNomePessoa(nomePessoa);
		
		String cadastrar = msg.recebeMensagem();
		boolean confere = true;

		while (confere) {
			if (cadastrar.trim().equalsIgnoreCase("s")) {

				String sql = "INSERT INTO registro_de_visitas "
						+ "(id, visitante, data_visita, hora_visita, id_pessoa, lugar) values"
						+ "( '" + reg.getPosicao() + "' , '"
						+ reg.getNomePessoa() + "' , '" + reg.getData()
						+ "' , '" + reg.getHora() + "' , '" + reg.getIDpessoa()
						+ "' , '" + reg.getLocal().trim() + "' );";
				try {
					PreparedStatement ps = ConectaBD.conexao()
							.prepareStatement(sql);
					ps.execute();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				msg.enviaMensagem("Registro adicionado!");
				confere = false;
			} else if (cadastrar.trim().equalsIgnoreCase("n")) {
				msg.enviaMensagem("Registro ignorado!");
				confere = false;
			} else {
				msg.enviaMensagem("Opcao invalida");
				msg.enviaMensagem("Digite uma opcao valida. (S/N)");
				cadastrar = msg.recebeMensagem();
				confere = true;
			}
		}
	}
}
