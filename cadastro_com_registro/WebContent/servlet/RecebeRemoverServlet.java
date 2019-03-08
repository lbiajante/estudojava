package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.RemoverLocal;
import registro.RemoverRegistro;
import cadastro.Remover;
import utilitarias_cadastro_manual.ValidaId;

@WebServlet("/RecebeRemoverServlet")
public class RecebeRemoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ValidaId validaId = new ValidaId();
	Remover remCad = new Remover();
	RemoverRegistro remReg = new RemoverRegistro();
	RemoverLocal remLocal = new RemoverLocal();

	public RecebeRemoverServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idCadastro = request.getParameter("tIdCadastro");
		String idRegistro = request.getParameter("tIdRegistro");
		String idLocal = request.getParameter("tIdLocal");
		PrintWriter writer = response.getWriter();
		
		boolean retorno = false;

		if (idCadastro != null) {
			String rc = remCad.removerCadastro(idCadastro);
			if (rc.equals("removido")) {
				writer.println("<p>Cadastro removido com sucesso!</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			} else if (rc.equals("registro")) {
				writer.println("<script>confirm('Cadastro nao pode ser removido porque esta vinculado a um registro de visitas');</script>");
				retorno = true;
			} else if (rc.equals("NID")) {
				writer.println("<script>confirm('Nao existe cadastro com esse ID');</script>");
				retorno = true;
			}
			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			}
		}
		if (idRegistro != null) {
			String rr = remReg.removerRegistro(idRegistro);
			if (rr.equals("removido")) {
				writer.println("<p>Registro removido com sucesso!</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			
			} else if (rr.equals("NReg")) {
				writer.println("<script>confirm('Nao existe registro com esse ID');</script>");
				retorno = true;
			}
			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			}
		}

		if (idLocal != null) {
			String rl = remLocal.removerLocal(idLocal);
			if (rl.equals("removido")) {
				writer.println("<p>Local removido com sucesso!</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			} else if (rl.equals("registro")) {
				writer.println("<script>confirm('Local nao pode ser removido porque esta vinculado a um registro de visitas');</script>");
				retorno = true;
			} else if (rl.equals("NLocal")) {
				writer.println("<script>confirm('Esse lugar nao pode ser removido, pois nao esta cadastrado');</script>");
				retorno = true;
			}
			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			}
		}
	}

}
