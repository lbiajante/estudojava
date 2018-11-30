package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
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

		PrintWriter writer = response.getWriter();

		writer.println("<h1>Menu Principal</h1>");
		writer.println("<html><head><title>Menu</title></head>"
				+ "<body><form action=RecebeMenuServlet method='POST'><fieldset id='listar'>"
				+ "<legend>Menu:</legend>"
				+ "<input type='radio' name='tmenu' id='cIncluir' value='incluir'/><label for='cIncluir'>Incluir Cadastro, Registros, Lugares</label><br/>"
				+ "<input type='radio' name='tmenu' id='cListagem' value='listar'/><label for='cListagem'>Listar Cadastro, Registro, Lugares</label><br/>"
				+ "<input type='radio' name='tmenu' id='cAtualizacao' value='atualizar'/><label for='cAtualizacao'>Atualizar Cadastro, Registro, Lugares</label><br/>"
				+ "<input type='radio' name='tmenu' id='cRemover' value='remover'/><label for='cRemover'>Remover Cadastro, Registro, Lugares</label><br/>"

				+ "</fieldset>"
				+ "<input type='image' name='Enviar' src='_imagens/botao-enviar.png' value='Enviar' onclick='RecebeMenuServlet'>"
				+ "</form></body></html>");

	}
}
