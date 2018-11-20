package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RecebeMenuServlet")
public class RecebeMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecebeMenuServlet() {
        super();
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String opcao = request.getParameter ("tmenu");
		String opDispatcher = null;
		if (opcao.equals("incluir")){
			opDispatcher = "IncluirCadastroServlet";
			
		}else if (opcao.equals("listar")){
			opDispatcher = "ListaServlet";
			
		}else if (opcao.equals("atualizar")){
			writer.println("<h1>A: " + opcao + "</h1>");
		}else if (opcao.equals("remover")){
			writer.println("<h1>R: " + opcao + "</h1>");
		}
		RequestDispatcher acesso = request.getRequestDispatcher(opDispatcher);
		acesso.forward(request,response);
		
	}

}
