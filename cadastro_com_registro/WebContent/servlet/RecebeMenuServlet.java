package servlet;

import java.io.IOException;

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
		
		String opcao = request.getParameter ("tmenu");
		String opDispatcher = null;
		if (opcao.equals("incluir")){
			opDispatcher = "IncluirServlet";
			
		}else if (opcao.equals("listar")){
			opDispatcher = "ListaServlet";
			
		}else if (opcao.equals("atualizar")){
			opDispatcher = "AtualizarServlet";
			
		}else if (opcao.equals("remover")){
			opDispatcher = "RemoverServlet";
		}
		RequestDispatcher acesso = request.getRequestDispatcher(opDispatcher);
		acesso.forward(request,response);
		
	}

}
