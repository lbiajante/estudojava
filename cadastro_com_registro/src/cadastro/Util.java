package cadastro;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {
	ArrayList<CadastroEmArquivo> lista = new ArrayList<CadastroEmArquivo>();
	CadastroEmArquivo cad = new CadastroEmArquivo();
	Scanner entrada = new Scanner(System.in);
	private String path;
	// ------------------GERAR ARQUIVO -------------
	public String gerarArquivo() {
		boolean confere = true;
		path = textInput("Digite o nome do arquivo a ser criado ou lido");
		path = path.trim();
		while (confere) {
			if (path.isEmpty() || path.trim().equals("")
					|| path.trim().equals(null)) {
				System.out
						.println("O campo nome do arquivo nao pode ser em branco");
				path = textInput("Digite o nome do arquivo");
				confere = true;
			} else {
				confere = false;
			}
		}
		//path = path + ".txt";
		try {
			FileWriter criadorDeArquivo = new FileWriter(path, true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
			
		} catch (IOException e) {
			System.out.println("Erro na criacao do arquivo");
		}
		return path;
	}

	public void escreverNoArquivo(CadastroEmArquivo cad,
			ArrayList<CadastroEmArquivo> cadastroEmArquivo, String nomeArq) {
		this.lista = cadastroEmArquivo;
		this.cad = cad;
		try {
			FileOutputStream arq = new FileOutputStream(nomeArq, true);
			ObjectOutputStream objOutput = new ObjectOutputStream(arq);
			objOutput.writeObject(cad);
			objOutput.flush();
			objOutput.close();
			arq.flush();
			arq.close();

		} catch (IOException erro) {
			erro.printStackTrace();
		}
	}

	public ArrayList<CadastroEmArquivo> lerArquivo(String path) {
		CadastroEmArquivo cad = new CadastroEmArquivo();
		FileInputStream path1 = null;
		lista.clear();
		try {
			path1 = new FileInputStream(path);
			while (true) {
				ObjectInputStream objInput = new ObjectInputStream(path1);
				cad = (CadastroEmArquivo) objInput.readObject();
				this.lista.add(cad);
			}
		} catch (EOFException eof) {
			return this.lista;
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (path1 != null)
				try {

					path1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return this.lista;
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
