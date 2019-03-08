package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registro.CadastrarRegistro;
import local.CadastrarLocal;
import utilitarias_cadastro_manual.ValidaCPF;
import utilitarias_cadastro_manual.ValidaId;
import cadastro.Cadastrar;

@WebServlet("/RecebeCadastroServlet")
public class RecebeCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ValidaCPF validaCpf = new ValidaCPF();
	ValidaId validaId = new ValidaId();
	Cadastrar cad = new Cadastrar();
	CadastrarLocal cadLocal = new CadastrarLocal();
	CadastrarRegistro cadReg = new CadastrarRegistro();

	public RecebeCadastroServlet() {
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
		String id = request.getParameter("tId");
		String local = request.getParameter("tLocal");
		String idVisita = request.getParameter("tIdVisita");

		// bloco cadastro de pessoas
		if (id != (null)) {
			String nome = request.getParameter("tNome");
			String cpf = request.getParameter("tCpf");
			String cel = request.getParameter("tCel");
			String data = request.getParameter("tData");
			String emp = request.getParameter("tEmpresa");
			String area = request.getParameter("tArea");

			id = validaId.verificaID(id);
			cpf = validaCpf.validarCPF(cpf);
			boolean retorno = false;

			if (id.equals("000000")) {
				writer.println("<script>confirm('ID esta sendo usado');</script>");
				retorno = true;
			}
			if (cpf.equals("nulo")) {
				writer.println("<script>confirm('CPF invalido');</script>");
				retorno = true;
			}
			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='IncluirServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='IncluirServlet'> </form>");
			} else {
				String c = cad.cadastrar(id, nome, cpf, cel, data, emp, area);
				writer.println("<p>" + c + "</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			}
		}
		// bloco registro de visitas
		if (idVisita != null) {
			boolean retorno = false;
			String visitante = cadReg.confereRegistro(idVisita);
			String[] splitVisitante;

			if (visitante.equals("NCAD")) {
				writer.println("<script>confirm('Não tem pessoa cadastrada com esse ID');</script>");
				retorno = true;
			}

			else {
				splitVisitante = visitante.split(",");
				String idVisitante = splitVisitante[0];
				String nomeVisitante = splitVisitante[1];
		//		String idReg = request.getParameter("tIdPessoaRegistro");
				String data = request.getParameter("tDataVisita");
				String hora = request.getParameter("tHoraVisita");
				String localVis = request.getParameter("tLocalVisita");

				String cadLocalVis = cadLocal.cadastrarLocal(localVis);
				String cR = cadReg.cadastrar(idVisitante, nomeVisitante,
						data, hora, localVis);

				writer.println("<p>" + cR + "</p>");
				writer.println("<p>" + cadLocalVis + "</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			}

			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='IncluirServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='IncluirServlet'> </form>");
			}
		}
		// bloco local
		if (local != null) {
			String m = cadLocal.cadastrarLocal(local);
			writer.println("<h1>" + local + "</h1>");
			writer.println("<p>" + m + "</p>");
			writer.println("<form method='post' id='fReturn' action='MainServlet'>"
					+ "<input type='submit' name='tRetornar' value='Retornar' "
					+ "onclick='MainServlet'> </form>");
		}
	}
}
