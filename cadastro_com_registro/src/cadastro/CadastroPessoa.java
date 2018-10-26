package cadastro;

import java.io.Serializable;

public class CadastroPessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String posicao;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String empresa;
	private String areaDeAtuacao;
	private String celular;

	public CadastroPessoa() {
		super();
	}

	public CadastroPessoa(String nome, String dataNascimento, String cpf,
			String empresa, String areaDeAtuacao, String celular, String posicao) {

		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.empresa = empresa;
		this.areaDeAtuacao = areaDeAtuacao;
		this.celular = celular;
		this.posicao = posicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getAreaDeAtuacao() {
		return areaDeAtuacao;
	}

	public void setAreaDeAtuacao(String areaDeAtuacao) {
		this.areaDeAtuacao = areaDeAtuacao;
	}

	@Override
	public String toString() {
		CadastroPessoa cadastroEmArquivo = this;
		return "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\nID: "
				+ cadastroEmArquivo.getPosicao() + "\nNome: "
				+ cadastroEmArquivo.getNome() + "\nData de nascimento: "
				+ cadastroEmArquivo.getDataNascimento() + "\nCPF: "
				+ cadastroEmArquivo.getCpf() + "\nCelular: "
				+ cadastroEmArquivo.getCelular() + "\nEmpresa: "
				+ cadastroEmArquivo.getEmpresa() + "\nArea de Atuacao: "
				+ cadastroEmArquivo.getAreaDeAtuacao()
				+ "\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n";
	}
}
