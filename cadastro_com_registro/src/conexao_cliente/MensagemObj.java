package conexao_cliente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MensagemObj implements Serializable {

	private String mensagem;
	private Status status;
	private Referencia refer;
	
	Map <Referencia, Object> params;
	

	public MensagemObj() {	
	
	}
	
	public MensagemObj(Referencia refer, String mensagem) {		
		this.mensagem = mensagem;
		this.refer = refer;
		params = new HashMap<>();
	}
	public Referencia getRefer() {
		return refer;
	}
	
	public void setRefer(Referencia refer) {
		this.refer = refer;
	}	

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public void setStatus(Status s) {
		this.status = s;
	}

	public Status getStatus() {
		return status;
	}

	public void setParam(Referencia refer, Object valor) {
		params.put(refer, valor);
	}

	public Object getParam(Referencia refer) {
		return params.get(refer);
	}

	@Override
	public String toString() {
		String m = mensagem;
	
		return m;
	}

}
