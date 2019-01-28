package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.CadastrarLocal;
import registro.CadastrarRegistro;

public class MainServletLinhaDeComando extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CadastrarLocal cadLocal = new CadastrarLocal();
	CadastrarRegistro cadReg = new CadastrarRegistro();
	static String local;
	static String data;
	static String hora;

	public static void main(String[] args) {
		if (args.length > 0) {
			local = args[0];
			data = args[1];
			hora = args[2];	

		
		}
	}

}
		

