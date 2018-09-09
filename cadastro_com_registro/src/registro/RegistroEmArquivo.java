package registro;

import java.io.Serializable;

public class RegistroEmArquivo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String posicao;
	private String local;
	private String data;
	private String hora;
	private String IDpessoa;
	private String nomePessoa;

	public String getIDpessoa() {
		return IDpessoa;
	}

	public void setIDpessoa(String iDpessoa) {
		IDpessoa = iDpessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public RegistroEmArquivo() {
		super();
	}

	public RegistroEmArquivo(String posicao, String local, String data,
			String hora, String iDpessoa, String nomePessoa) {
		super();
		this.posicao = posicao;
		this.local = local;
		this.data = data;
		this.hora = hora;
		IDpessoa = iDpessoa;
		this.nomePessoa = nomePessoa;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		RegistroEmArquivo registroEmArquivo = this;
		return "Local Visitado: ID - " + registroEmArquivo.getPosicao()
				+ ", Local - " + registroEmArquivo.getLocal() + ", Data - "
				+ registroEmArquivo.getData() + ", Hora - "
				+ registroEmArquivo.getHora() + "\n Visitado por:  ID - "
				+ registroEmArquivo.getIDpessoa() + ", Nome - "
				+ registroEmArquivo.getNomePessoa() + ".\n";
	}
}
