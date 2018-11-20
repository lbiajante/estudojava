package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.CadastrarLocal;

/**
 * Servlet implementation class RecebeLocalServlet
 */
public class RecebeLocalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       CadastrarLocal cadLocal = new CadastrarLocal();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecebeLocalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String local = request.getParameter("tLocal");
		
		String m = cadLocal.cadastrarLocal(local);
		
		PrintWriter writer = response.getWriter();
		writer.println("<h1>"+local+"</h1>");
		writer.println("<p>"+m+"</p>");
		
	
	}

}
