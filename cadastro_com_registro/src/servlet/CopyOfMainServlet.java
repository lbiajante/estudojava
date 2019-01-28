package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.CadastrarLocal;
import registro.CadastrarRegistro;


//@WebServlet("/MainServlet")
public class CopyOfMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CadastrarLocal cadLocal = new CadastrarLocal();
	CadastrarRegistro cadReg = new CadastrarRegistro();

	public CopyOfMainServlet() {
		super();
	}


	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
						
				String idVisitante = "000001";
				String nomeVisitante = "leandro";			
				String data = MainServletLinhaDeComando.data;
				String hora = MainServletLinhaDeComando.hora;
				String localVis = MainServletLinhaDeComando.local;
				String cadLocalVis = cadLocal.cadastrarLocal(localVis);
				String cR = cadReg.cadastrar(idVisitante, nomeVisitante, data, hora, localVis);

				System.out.println(cR);
				System.out.println(cadLocalVis);
				System.exit(0);
		}
}
