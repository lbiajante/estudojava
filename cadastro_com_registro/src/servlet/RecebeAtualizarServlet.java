package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.CadastrarLocal;
import registro.AtualizarRegistro;
import utilitarias_cadastro_manual.ValidaCPF;
import utilitarias_cadastro_manual.ValidaId;
import cadastro.Atualizar;

public class RecebeAtualizarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Atualizar atualCad = new Atualizar();
	CadastrarLocal cadLocal = new CadastrarLocal();
	AtualizarRegistro atualReg = new AtualizarRegistro();
	ValidaId validaId = new ValidaId();
	ValidaCPF validaCpf = new ValidaCPF();

	public RecebeAtualizarServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idCadastro = request.getParameter("tIdCadastro");
		String idRegistro = request.getParameter("tIdVisita");

		PrintWriter writer = response.getWriter();

		if (idCadastro != null) {
			String id = idCadastro;
			idCadastro = validaId.verificaID(idCadastro);
			boolean retorno = false;
			if (idCadastro.equals("000000")) {
				String nome = request.getParameter("tNome");
				String cpf = request.getParameter("tCpf");
				String cel = request.getParameter("tCel");
				String data = request.getParameter("tData");
				String emp = request.getParameter("tEmpresa");
				String area = request.getParameter("tArea");
				cpf = validaCpf.validarCPF(cpf);

				if (cpf.equals("nulo")) {
					writer.println("<script>confirm('CPF invalido');</script>");
					retorno = true;
				} else {
					String a = atualCad.atualizarCadastro(id, nome, cpf, cel,
							data, emp, area);
					writer.println("<p>" + a + "</p>");
					writer.println("<form method='post' id='fReturn' action='MainServlet'>"
							+ "<input type='submit' name='tRetornar' value='Retornar' "
							+ "onclick='MainServlet'> </form>");
				}
			} else {
				writer.println("<script>confirm('Não tem cadastro com esse ID para ser atualizado');</script>");
				retorno = true;
			}
			if (retorno) {
				writer.println("<form method='post' id='fReturn' action='IncluirServlet'>"
						+ "<p>Clique no botao abaixo para preencher o formulario novamente</p>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='IncluirServlet'> </form>");
			}
		}

		if (idRegistro != null) {
			
			String	data = request.getParameter("tDataVisita");
			String	hora = request.getParameter("tHoraVisita"); 
			String	localVis = request.getParameter("tLocalVisita");
			String cl = cadLocal.cadastrarLocal(localVis);			
			String ar = atualReg.atualizarRegistro(idRegistro, localVis, data, hora);			
			
				writer.println("<p>" + ar + "</p>");
				writer.println("<form method='post' id='fReturn' action='MainServlet'>"
						+ "<input type='submit' name='tRetornar' value='Retornar' "
						+ "onclick='MainServlet'> </form>");
			
			}
		}
	}


