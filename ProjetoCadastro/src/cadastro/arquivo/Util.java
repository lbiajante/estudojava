package cadastro.arquivo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Util {

	CadastroEmArquivo cad = new CadastroEmArquivo();
	Scanner entrada = new Scanner(System.in);
	String path;

	public int verificaCodigo() {
		int cod = 0;
		boolean confere = true;
		while (confere) {
			String codigo = entrada.nextLine();
			try {
				cod = Integer.parseInt(codigo);
				System.out.println(cod);
				if (cod <= 0) {
					System.out.println("O código precisa ser maior que zero");
					confere = true;
				} else {
					confere = false;
				}
			} catch (Exception e) {
				System.out.printf("Você não digitou um número inteiro!\n");
			}
		}
		return cod;
	}

	public void gerarArquivo() {

		try {			
			path = textInput("Digite o nome do arquivo a ser criado ou lido");
			cad.setNomeDoArquivo(path + ".txt");
			System.out.println(cad.getNomeDoArquivo());
			FileWriter criadorDeArquivo = new FileWriter(cad.getNomeDoArquivo(), true);
			criadorDeArquivo.flush();
			criadorDeArquivo.close();
		} catch (IOException e) {
			System.out.println("Erro na criação do arquivo");
		}
		path = cad.getNomeDoArquivo();
		System.out.println(path);		
	} 

	public void contarLinhas() {
		int numeroLinhas = 0;
			try {
				System.out.println(path);
			File arq = new File(path);
			long tamanhoCad = arq.length();
			FileInputStream fs = new FileInputStream(arq);
			DataInputStream in = new DataInputStream(fs);

			@SuppressWarnings("resource")
			LineNumberReader lineRead = new LineNumberReader(
					new InputStreamReader(in));
			lineRead.skip(tamanhoCad);
			numeroLinhas = lineRead.getLineNumber() + 1;
			cad.setPosicao(numeroLinhas);
			System.out.println("O ARQUIVO CONTEM " + numeroLinhas
					+ " LINHAS!!!!!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void escreverNoArquivo() {

		try {
			FileWriter fw = new FileWriter(cad.getNomeDoArquivo(), true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(this.cad.toString());
			pw.flush();
			pw.close();

		} catch (IOException e) {
			System.out.println("Erro ao cadastrar!");
		}
	}

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
