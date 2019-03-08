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
				+ "<h3>Main - Remover </h3>"
				+ "<h1>Cadastro de Pessoas, Registros de Visitas e Lugares</h1>"
				+ "<h2>por Leandro</h2><h3 class='dir'>Atualizado em nov/2018</h3></header>"

				+ "<form method='post' id='fRemoverCadastro' action='RecebeRemoverServlet'>"
				+ "<fieldset id='remover'><legend>Remover Cadastro de Pessoas</legend>"
				+ "<input type='number' name='tIdCadastro' id='cIdCadastro' size='6' maxlength='6' placeholder='999999' /></p>"
				+ "<input type='image' name='tEnviarRemoverCadastro' src='_imagens/botao-enviar.png' "
				+ "value='EnviarRemoverCadastro' onclick='RecebeRemoverServlet'>"
				+ "</fieldset></form>"
				
				+ "<form method='post' id='fRemoverRegistro' action='RecebeRemoverServlet'>"
				+ "<fieldset id='remover'><legend>Remover Registro de Visitas</legend>"
				+ "<input type='number' name='tIdRegistro' id='cIdRegistro' size='6' maxlength='6' placeholder='999999' /></p>"
				+ "<input type='image' name='tEnviarRemoverRegistro' src='_imagens/botao-enviar.png' "
				+ "value='EnviarRemoverRegistro' onclick='RecebeRemoverServlet'>"
				+ "</fieldset></form>"
				
				+ "<form method='post' id='fRemoverLocal' action='RecebeRemoverServlet'>"
				+ "<fieldset id='remover'><legend>Remover Local</legend>"
				+ "<input type='text' name='tIdLocal' id='cIdLocal' size='20' maxlength='20' placeholder='Lugar' /></p>"
				+ "<input type='image' name='tEnviarRemoverLocal' src='_imagens/botao-enviar.png' "
				+ "value='EnviarRemoverLocal' onclick='RecebeRemoverServlet'>"
				+ "</fieldset></form>");
}
}