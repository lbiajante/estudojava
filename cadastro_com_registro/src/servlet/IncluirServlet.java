package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/IncluirServlet")
public class IncluirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IncluirServlet() {
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

		writer.println("<header id='cabecalho-main'>"
				+ "<h3>Main - Cadastro</h3>"
				+ "<h1>Cadastro de Pessoas, Registros de Visitas e Lugares</h1>"
				+ "<h2>por Leandro</h2><h3 class='dir'>Atualizado em nov/2018</h3></header>"

				+ "<form method='post' id='fCadastro' action='RecebeCadastroServlet'>"
				+ "<fieldset id='cadastro'><legend>Cadastro de Pessoas</legend>"

				+ "<p><label class='cadastro' for='cId'>ID: </label>"
				+ "<input type='number' name='tId' id='cId' size='6' maxlength='6' placeholder='999999' required/></p>"

				+ "<p><label class='cadastro' for='cNome'>Nome: </label>"
				+ "<input type='text' name='tNome' id='cNome' size='20' maxlength='30' placeholder='Nome Completo' required/></p>"

				+ "<p><label class='cadastro' for='cCpf'>CPF: </label>"
				+ "<input type='text' name='tCpf' id='cCpf' size='20' maxlength='30' "
				+ "pattern='[0-9]{11}' placeholder='999.999.999-99' required/></p>"

				+ "<p><label class='cadastro' for='cData'>Data Nasc: </label>"
				+ "<input type='date' name='tData' id='cData' required/></p>"

				+ "<p><label class='cadastro' for='cEmpresa'>Empresa: </label>"
				+ "<input type='text' name='tEmpresa' id='cEmpresa' size='20' maxlength='30' "
				+ "placeholder='Nome da Empresa' required/></p>"

				+ "<p><label class='cadastro' for='cArea'>Área de Atuação: </label>"
				+ "<input type='text' name='tArea' id='cArea' size='20' maxlength='30' "
				+ "placeholder='Área de Atuação' required/></p>"

				+ "<p><label class='cadastro' for='cCel'>Celular: </label>"
				+ "<input type='tel' name='tCel' id='cCel' pattern='[0-9]{11}'"
				+ "placeholder='(99)99999-9999'/></p>"

				+ "<input type='image' name='tEnviarCadastro' src='_imagens/botao-enviar.png' "
				+ "value='EnviarCadastro' onclick='RecebeCadastroServlet'>"
				+ "</fieldset></form>"

				+ "<form method='post' id='fRegistro' action='RecebeRegistroServlet'>"
				+ "<fieldset id='registro'><legend>Registro de Visitas</legend>"

				+ "<p><label class='registro' for='cIdPessoaRegistro'>ID: </label>"
				+ "<input type='number' name='tIdPessoaRegistro' id='cIdPessoaRegistro'	size='6' maxlength='6' placeholder='999999'/></p>"

				+ "<p><label class='registro' for='cDataVisita'>Data Visita: </label>"
				+ "<input type='date' name='tDataVisita' id='cDataVisita'></p>"

				+ "<p><label class='registro' for='cHoraVisita'>Hora Visita: </label>"
				+ "<input type='time' name='tHoraVisita' id='cHoraVisita'></p>"

				+ "<p><label class='registro' for='cLocal'>Local: </label>"
				+ "<input type='text' name='tLocal' id='cLocal' maxlength='30' size='20'></p>"

				+ "<input type='image' name='tEnviarRegistro' src='_imagens/botao-enviar.png' value='EnviarRegistro' onclick='RecebeRegistroServlet'>"
				+ "</fieldset></form>"

				+ "<form method='post' id='fLocal' action='RecebeCadastroServlet'>"
				+ "<fieldset id='local'><legend>Cadastro de Novo Local</legend>"

				+ "<p><label class='local' for='cLocal'>Local: </label>"
				+ "<input type='text' name='tLocal' id='cLocal' maxlength='30' size='20'></p>"

				+ "<input type='image' name='tEnviarLocal' src='_imagens/botao-enviar.png'value='EnviarLocal' onclick='RecebeCadastroServlet'>"
				+ "</fieldset></form>");
	
	}
}
