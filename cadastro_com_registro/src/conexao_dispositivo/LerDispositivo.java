package conexao_dispositivo;

import java.io.IOException;
import java.net.Socket;

public class LerDispositivo {

	private ValidaDispositivo validaDispositivo = new ValidaDispositivo();

	public LerDispositivo(Socket cliente) {
		validaDispositivo.validaDispositivo(cliente);
		System.out.println("Registros inseridos no banco de dados com sucesso");
		try {
			cliente.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}