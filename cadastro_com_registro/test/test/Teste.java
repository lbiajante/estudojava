package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitarias.ValidaCelular;
import utilitarias.ValidaData;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;
import cadastro.Cadastrar;

public class Teste {

	@Test
	public void deveRetornarCelularValido() throws Exception {
		ValidaCelular celular = new ValidaCelular();
		System.out.println(celular.formatarCelular());
	}

	@Test
	public void deveRetornarDataEHoraFormatadas() throws Exception {
		ValidaData data = new ValidaData();
		String label1 = "Digite a data formato: ddmmyyyy";
		String label2 = "Digite a hora formato: hhmm";
		System.out.println(data.data(label1) + "    " + data.hora(label2));
	}

	@Test
	public void deveRetornarIdFormatado() throws Exception {
		ValidaId id = new ValidaId();
		assertEquals(id.confereID("1"), "000001");

	}

	@Test
	public void impedeStringsEmBranco() throws Exception {
		ValidaStrings string = new ValidaStrings();
		System.out.println(string.texto("", "sem espaço"));
		System.out.println(string.texto(" ", "sem espaço"));
		System.out.println(string.texto("qualquer", "qualquer texto"));
	}
}
