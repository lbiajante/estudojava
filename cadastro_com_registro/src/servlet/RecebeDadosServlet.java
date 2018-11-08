package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cadastro.CadastroPessoa;

@WebServlet(name = "Recebe Dados", urlPatterns = "/RecebeDadosServlet")
public class RecebeDadosServlet extends HttpServlet {

	private static final long serialVersionUID = -7891159096554766799L;
	ListaCadastroServlet listCad = new ListaCadastroServlet();
	String opcao;
	private List<CadastroPessoa> lis;

	public RecebeDadosServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		opcao = request.getParameter("tlistagem");
		if (opcao.equals("cadastro")) {
			writer.println("<h1>Lista solicitada "+ opcao +"</h1>");			
			 
			lis = listCad.listarCadastros();
		request.setAttribute("lis", lis);
		RequestDispatcher saida = request.getRequestDispatcher("RecebeDadosServlet");
		saida.forward(request, response);
		}
	}
}
