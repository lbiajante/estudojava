package cadastro.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Remover {
	

	private CadastroEmArquivo cad = new CadastroEmArquivo();
	private Util util = new Util();
	private Listar listar = new Listar();
	private ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();
	
	public void removerCadastro() {
		File arq = new File(cad.getNomeDoArquivo());
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;
			System.out.println("Indique o número de cadastro para remover: ");
			posicao = ("Posição: " + String.format("%03d",
					util.verificaCodigo())).trim();

			while (linha != null) {
				if (linha.contains(posicao) == false) {
					j++;
					String posicaoAntiga = linha.substring(0, 12);
					String posicaoNova = "Posição: "
							+ String.format("%03d", j).trim();
					String newLine = linha.replace(posicaoAntiga, posicaoNova);
					salvar.add(newLine);
				}
				linha = br.readLine();
			}

			br.close();
			fr.close();
			FileWriter fw2 = new FileWriter(arq, true);
			fw2.close();

			FileWriter fw = new FileWriter(arq);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < salvar.size(); i++) {
				bw.write(salvar.get(i));
				bw.newLine();
			}
			bw.close();
			fw.close();

			listar.importar(cadastroEmArquivo);
			System.out.println("Cadastro da " + posicao.toLowerCase()
					+ " removido com sucesso. Lista renumerada.");

		} catch (IOException e) {
			System.out.println("Erro ao tentar remover.");
		}

	}
}
