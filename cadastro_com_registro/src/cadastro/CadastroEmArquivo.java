package cadastro;

import java.io.Serializable;

public class CadastroEmArquivo implements Serializable {

	private String nomeDoArquivo;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String empresa;
	private String areaDeAtuacao;
	private String celular;
	private String posicao;

	public CadastroEmArquivo() {
		super();
	}

	public CadastroEmArquivo(String nomeDoArquivo, String nome,
			String dataNascimento, String cpf, String empresa,
			String areaDeAtuacao, String celular, String posicao) {
		super();
		this.nomeDoArquivo = nomeDoArquivo;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.empresa = empresa;
		this.areaDeAtuacao = areaDeAtuacao;
		this.celular = celular;
		this.posicao = posicao;
	}

	public String getNomeDoArquivo() {
		return nomeDoArquivo;
	}

	public void setNomeDoArquivo(String nomeDoArquivo) {
		this.nomeDoArquivo = nomeDoArquivo;
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
		CadastroEmArquivo cadastroEmArquivo = this;
		return "Posicao: " + cadastroEmArquivo.getPosicao() + " - Nome: "
				+ cadastroEmArquivo.getNome() + " - Data de nascimento: "
				+ cadastroEmArquivo.getDataNascimento() + " - CPF: "
				+ cadastroEmArquivo.getCpf() + " - Empresa: "
				+ cadastroEmArquivo.getAreaDeAtuacao() + " - Area de Atuacao: "
				+ cadastroEmArquivo.getEmpresa() + " - Celular: "
				+ cadastroEmArquivo.getCelular() + ".";
	}

}
