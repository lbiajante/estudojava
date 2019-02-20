package registro_terminal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletRegistroTerminal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	
    	/*reg.setLocal(cld.cadastrarLocal(lugarS.get(i)));
				reg.setData(fdd.data(dataS.get(i)));
				reg.setHora(fdd.hora(horaS.get(i)));
				reg.setIDpessoa(IdPessoa);
				reg.setNomePessoa(nomePessoa);*/
	}

}
