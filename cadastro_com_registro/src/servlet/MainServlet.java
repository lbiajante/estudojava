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
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
			}
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
PrintWriter writer = response.getWriter();
		
		writer.println("<h1>Listagem de Cadastro de Pessoas,"
				+ " Registros de Visitas e	Lugares</h1>");
		writer.println("<html><head><title>Listagem</title></head>"
				+ "<body><form action=RecebeDadosServlet method='POST'><fieldset id='listar'>"
				+ "<legend>Listagem:</legend>"
				+ "<input type='radio' name='tlistagem' id'cCadastro' value='cadastro'/><label for='cCadastro'>Cadastro de Pessoas</label><br/>"
				+ "<input type='radio' name='tlistagem' id='cRegistro' value='registro'/><label for='cRegistro'>Registro de Visitas</label><br/>"
				+ "<input type='radio' name='tlistagem' id='cLugar' value='lugar'/><label for='cLugar'>Lugares Visitados</label>"
				+ "</fieldset>"
				+ "<input name='Enviar' type='submit' value='Enviar'> "
				+ "</form></body></html>");
		
	
	}
}
