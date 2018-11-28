package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoverServlet
 */
@WebServlet("/RemoverServlet")
public class RemoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter writer = response.getWriter();
		writer.println("<header id='cabecalho-main'>"
				+ "<h3>Main - Remover Cadastro</h3>"
				+ "<h1>Cadastro de Pessoas, Registros de Visitas e Lugares</h1>"
				+ "<h2>por Leandro</h2><h3 class='dir'>Atualizado em nov/2018</h3></header>"

				+ "<form method='post' id='fRemover' action='RecebeRemoverServlet'>"
				+ "<fieldset id='remover'><legend>Remover Cadastro de Pessoas</legend>"
				+ "<input type='number' name='tId' id='cId' size='6' maxlength='6' placeholder='999999' required/></p>"
				+ "<input type='image' name='tEnviarRemover' src='_imagens/botao-enviar.png' "
				+ "value='EnviarRemover' onclick='RecebeRemoverServlet'>"
				+ "</fieldset></form>");
}
}