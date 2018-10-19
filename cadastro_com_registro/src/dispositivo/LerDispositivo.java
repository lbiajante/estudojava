package dispositivo;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import local.CadastrarLocal;
import local.Local;
import registro.RegistroVisita;
import uteis.ConectaBD;
import uteis.ValidaId;
import conexao_cliente.Gerenciador;
import conexao_cliente.ServidorSocket;

public class LerDispositivo {

	
	private ServidorSocket client = new ServidorSocket();
	private Socket cliente;
	private Gerenciador msg = new Gerenciador(cliente);
	private ValidaId validaId = new ValidaId();
	private ValidaDispositivo validaDispositivo = new ValidaDispositivo();

	public LerDispositivo(Socket cliente) {
		this.cliente = cliente;
		validaDispositivo.validaDispositivo(msg);
	}


	
	
}