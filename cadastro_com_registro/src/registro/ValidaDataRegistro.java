package registro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidaDataRegistro {
	private Scanner entrada = new Scanner(System.in);
	
	public String hora (){
		String hora = null;
		return hora;
	}
	
	public String data() {
		boolean confere = true;
		Date dtns = null;

		while (confere) {
		
			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out
						.println("Digite a data da visita no formato: ddmmaaaa");
				dataFormatIn.setLenient(false);
				Date date = dataFormatIn.parse(entrada.nextLine().trim());
				dtns = date;
				confere = false;
			} catch (Exception e) {
				System.out.println("Data invalida");
			}
		}
		String dataVisita = null;
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("yyyy-MM-dd");
			String dataToString = dataFormatOut.format(dtns);
			dataVisita = dataToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataVisita;
	}

}
