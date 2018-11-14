package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.ListarLocais;
import registro.ListarRegistro;
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

			writer.println("<style>table{font-family: arial, sans-serif; border-collapse: collapse; width: 100%;}"
					+ " td, th {border: 1px solid #dddddd; text-align: center; padding: 8px;}"
					+ "tr:nth-child(even) {background-color: #dddddd;}</style>");
			writer.println("<table border=1><tr><caption>Cadastro</caption></tr>");
			writer.println("<tr><th>ID</th><th>Nome</th><th>CPF</th>"
					+ "<th>Celular</th><th>Empresa</th>"
					+ "<th>Area de Atuacao</th></tr>");

			for (int i = 0; i < l.size(); i++) {

				writer.println("<tr><td>" + listCad.getListId().get(i)
						+ "</td>");
				writer.println("<td>" + listCad.getListNome().get(i) + "</td>");
				writer.println("<td>" + listCad.getListCpf().get(i) + "</td>");
				writer.println("<td>" + listCad.getListCel().get(i) + "</td>");
				writer.println("<td>" + listCad.getListEmp().get(i) + "</td>");
				writer.println("><td>" + listCad.getListArea().get(i)
						+ "</td></tr>");
			}
			writer.println("</table>");
		} else if (opcao.equals("registro")) {
			writer.println("<h1>Lista solicitada: " + opcao + "</h1>");
			l = listReg.listarRegistros();

			writer.println("<style>table{font-family: arial, sans-serif; border-collapse: collapse; width: 100%;}"
					+ " td, th {border: 1px solid #dddddd; text-align: center; padding: 8px;}"
					+ "tr:nth-child(even) {background-color: #dddddd;}</style>");
			writer.println("<table border=1><tr><caption>Registro</caption></tr>");
			writer.println("<tr><th>ID Visita</th><th>Lugar</th><th>Data Visita</th>"
					+ "<th>Hora Visita</th><th>ID Visitante</th>"
					+ "<th>Visitante</th></tr>");

			for (int i = 0; i < l.size(); i++) {

				writer.println("<tr><td>" + listReg.getListRegId().get(i)
						+ "</td>");
				writer.println("<td>" + listReg.getListRegLugar().get(i)
						+ "</td>");
				writer.println("<td>" + listReg.getListRegData().get(i)
						+ "</td>");
				writer.println("<td>" + listReg.getListRegHora().get(i)
						+ "</td>");
				writer.println("<td>" + listReg.getListRegIdPessoa().get(i)
						+ "</td>");
				writer.println("><td>" + listReg.getListRegVisit().get(i)
						+ "</td></tr>");
			}
			writer.println("</table>");
		} else if (opcao.equals("lugar")) {

			writer.println("<h1>Lista solicitada: " + opcao + "</h1>");
			l = listLocal.listarLocais();

			writer.println("<style>table{font-family: arial, sans-serif; border-collapse: collapse; width: 100%;}"
					+ " td, th {border: 1px solid #dddddd; text-align: center; padding: 8px;}"
					+ "tr:nth-child(even) {background-color: #dddddd;}</style>");
			writer.println("<table border=1><tr><caption>Lugares</caption></tr>");
			writer.println("<tr><th>Lugar</th></tr>");

			for (int i = 0; i < l.size(); i++) {
				writer.println("<tr><td>" + listLocal.getListLugar().get(i)
						+ "</td></tr>");
			}
			writer.println("</table>");
		}
	}
}
