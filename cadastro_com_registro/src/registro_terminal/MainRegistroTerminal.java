package registro_terminal;

import utilitarias_cadastro_manual.FormataDataDispositivo;

public class MainRegistroTerminal {

	public static void main(String[] args) {
		FormataDataDispositivo date = new FormataDataDispositivo();
		
		String local = args[0];
		String data = args [1];
		String hora = args[2];
		data = date.data(data);
		hora = date.hora(hora);
				
		System.out.println("Local: " + local);
		System.out.println("Data: " + data);
		System.out.println("Hora: " + hora);
	}
}
