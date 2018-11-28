package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilitarias_cadastro_manual.ValidaId;

@WebServlet("/RecebeRemoverServlet")
public class RecebeRemoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ValidaId validaId = new ValidaId();

	public RecebeRemoverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tId");
	

		PrintWriter writer = response.getWriter();
		id = validaId.verificaID(id);
		
		if (id.equals("000000")) {
			writer.println("<script>confirm('ID esta sendo usado');</script>");
		
		}

	}

}
