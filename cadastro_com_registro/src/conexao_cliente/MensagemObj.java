package conexao_cliente;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MensagemObj implements Serializable {

	private String operacao;
	private Status status;
	Map <Referencia, Object> params;

	public MensagemObj(String opcao) {
		this.operacao = opcao;
		params = new HashMap<>();
	}

	public String getOperacao() {
		return operacao;
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
		String m = operacao;
	
		return m;
	}

}
