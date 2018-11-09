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

import local.ListarLocais;
import registro.ListarRegistro;
import cadastro.CadastroPessoa;
import cadastro.Listar;

@WebServlet(name = "Recebe Dados", urlPatterns = "/RecebeDadosServlet")
public class RecebeDadosServlet extends HttpServlet {

	private static final long serialVersionUID = -7891159096554766799L;

	Listar listCad = new Listar();
	ListarRegistro listReg = new ListarRegistro();
	ListarLocais listLocal = new ListarLocais();
	String opcao;
	private List<String> l;

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
			writer.println("<h1>Lista solicitada: " + opcao + "</h1>");
			l = listCad.listarCadastros();
			for (int i = 0; i < l.size(); i++) {
				writer.println(l.get(i) + "</br></br>");
			}
		} else if (opcao.equals("registro")) {
			writer.println("<h1>Lista solicitada: " + opcao + "</h1>");
			l = listReg.listarRegistros();
			for (int i = 0; i < l.size(); i++) {
				writer.println(l.get(i) + "</br></br>");
			}
		} else if (opcao.equals("lugar")) {
			writer.println("<h1>Lista solicitada: " + opcao + "</h1>");
			l = listLocal.listarLocais();
			for (int i = 0; i < l.size(); i++) {
				writer.println(l.get(i) + "</br></br>");
			}
		}
	}
}
