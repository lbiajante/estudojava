package registro_terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import utilitarias_cadastro_manual.FormataDataDispositivo;

public class MainRegistroTerminal {

	public static void main(String[] args) {
		FormataDataDispositivo date = new FormataDataDispositivo();
	//	ServletRegistroTerminal srt = new ServletRegistroTerminal();

		String local = args[0];
		String data = args[1];
		String hora = args[2];
		data = date.data(data);
		hora = date.hora(hora);
		// String x = (local+"|"+data+"|"+hora);

		try {
			URL url = new URL(
					"http://localhost:8081/Cadastro/ServletRegistroTerminal");
			InputStream is = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
//			srt.recebeDadosMainTerminal(local, data, hora);
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			System.out.println("PPPPPPPPP");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
