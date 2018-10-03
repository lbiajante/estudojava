package uteis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ValidaData {
	private Scanner entrada = new Scanner(System.in);

	public String data(String label) {
		boolean confere = true;
		Date data = null;
		// laço para garantir a entrada de dados válidos
		while (confere) {
			try {
				// rotina de validação das datas
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				System.out.println(label);
				dataFormatIn.setLenient(false);
				Date date = dataFormatIn.parse(entrada.nextLine().trim());
				data = date;
				confere = false;
			} catch (Exception e) {
				System.out.println("Data invalida");
			}
		}
		String dt = null;
		// rotina de formatação da data validada
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd/MM/yyyy");
			String dataToString = dataFormatOut.format(data);
			dt = dataToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	// método de formatar hora
	public String hora(String label) {
		boolean confere = true;
		Date tmns = null;

		while (confere) {
			// rotina de validar hora
			try {
				SimpleDateFormat timeFormatIn = new SimpleDateFormat("HHmm");
				System.out.println(label);
				timeFormatIn.setLenient(false);
				Date time = timeFormatIn.parse(entrada.nextLine().trim());
				tmns = time;
				confere = false;
			} catch (Exception e) {
				System.out.println("Hora invalida");
			}
		}
		String horaVisita = null;
		// rotina de formatar hora validada
		try {
			SimpleDateFormat timeFormatOut = new SimpleDateFormat("HH:mm");
			String timeToString = timeFormatOut.format(tmns);
			horaVisita = timeToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return horaVisita;
	}

}
