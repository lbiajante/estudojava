package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registro.CadastrarRegistro;

@WebServlet("/ServletRegistroTerminal")  
public class ServletRegistroTerminal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CadastrarRegistro cadReg = new CadastrarRegistro();
	private String local;
	private String date;
	private String horario;
    
    public ServletRegistroTerminal() {
        super();
    }
    
    public void recebeDadosMainTerminal (String lugar, String data, String hora){
    	    	
    	local = lugar;
    	date = data;
    	horario = hora;    	
    	//request
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

    	 String localServlet = request.getParameter(local);
    	 String dateServlet = request.getParameter(date);
    	 String horarioServlet = request.getParameter(horario);
    	 writer.println("<h1>Menu Principal</h1>");
    	 writer.println("<p>" + localServlet + "</p>");
    	//String resposta = cadReg.cadastrar(localServlet, dateServlet, horarioServlet);  	 
	}
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	
    }

}
