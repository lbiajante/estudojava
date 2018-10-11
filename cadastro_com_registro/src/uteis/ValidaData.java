package uteis;

import java.text.SimpleDateFormat;
import java.util.Date;

import conexao_cliente.Gerenciador;

public class ValidaData {

	public String data(String label, Gerenciador msg) {
		boolean confere = true;
		Date data = null;
		while (confere) {
			try {
				SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
				msg.enviaMensagem(label);
				dataFormatIn.setLenient(false);
				Date date = dataFormatIn.parse(msg.recebeMensagem().trim());
				data = date;
				confere = false;
			} catch (Exception e) {
				msg.enviaMensagem("Data invalida");
			}
		}
		String dt = null;
		try {
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd/MM/yyyy");
			String dataToString = dataFormatOut.format(data);
			dt = dataToString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	public String hora(String label, Gerenciador msg) {
		boolean confere = true;
		Date tmns = null;

		while (confere) {
			try {
				SimpleDateFormat timeFormatIn = new SimpleDateFormat("HHmm");
				msg.enviaMensagem(label);
				timeFormatIn.setLenient(false);
				Date time = timeFormatIn.parse(msg.recebeMensagem().trim());
				tmns = time;
				confere = false;
			} catch (Exception e) {
				msg.enviaMensagem("Hora invalida");
			}
		}
		String horaVisita = null;
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
