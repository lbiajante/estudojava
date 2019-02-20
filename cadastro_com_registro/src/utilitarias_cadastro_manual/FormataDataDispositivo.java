package utilitarias_cadastro_manual;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataDataDispositivo {

	public String data(String label) {
		String dataToString = null;
		try {
			Date data = null;
			SimpleDateFormat dataFormatIn = new SimpleDateFormat("ddMMyyyy");
			dataFormatIn.setLenient(false);
			data = dataFormatIn.parse(label);
			SimpleDateFormat dataFormatOut = new SimpleDateFormat("dd/MM/yyyy");
			dataToString = dataFormatOut.format(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataToString;
	}

	public String hora(String label) {

		Date tmns = null;
		String timeToString = null;
		try {
			SimpleDateFormat timeFormatIn = new SimpleDateFormat("HHmm");
			timeFormatIn.setLenient(false);
			Date time = timeFormatIn.parse(label);
			tmns = time;
			SimpleDateFormat timeFormatOut = new SimpleDateFormat("HH:mm");
			timeToString = timeFormatOut.format(tmns);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeToString;
	}

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
