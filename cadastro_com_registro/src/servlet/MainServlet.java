package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Ola, Fulano!</title></head>"
				+ "<body><form action=RecebeDadosServlet><fieldset id='listar'>"
				+ "<legend>Listagem:</legend><input type='radio' name='tlistagem' id'cCadastro'/>"
				+ "<label for='cCadastro'>Cadastro de Pessoas</label><br/>"
				+ "<input type='radio' name='tlistagem' id='cRegistro'/>"
				+ "<label for='cRegistro'>Registro de Visitas</label><br/>"
				+ "<input type='radio' name='tlistagem' id='cLugar'/>"
				+ "<label for='cLugar'>Lugares Visitados</label></fieldset>"
				+ "</form></body></html>");
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	
}
