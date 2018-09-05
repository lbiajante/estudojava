package registro;

import java.io.Serializable;

public class RegistroEmArquivo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String posicao;
	private String local;
	private String data;
	private String hora;
	public RegistroEmArquivo() {
		super();
	}
	public RegistroEmArquivo(String posicao, String local, String data,
			String hora) {
		super();
		this.posicao = posicao;
		this.local = local;
		this.data = data;
		this.hora = hora;
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
}
