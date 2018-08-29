package cadastro.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Atualizar {
	Util util = new Util();
	Scanner entrada = new Scanner(System.in);
	ValidaData data = new ValidaData();
	ValidaCPF cpf = new ValidaCPF();
	ValidaCelular celular = new ValidaCelular();
	CadastroEmArquivo cad = new CadastroEmArquivo();
	ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();


	public void atualizarCadastro() {
		File arq = new File(cad.getNomeDoArquivo());
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			System.out.println("Indique o número de cadastro para atualizar: ");
			int cod2 = util.verificaCodigo();
			posicao = ("Posição: " + String.format("%03d", cod2));

			while (linha != null) {
				if (linha.contains(posicao) == false) {
					salvar.add(linha);
				} else if (linha.contains(posicao) == true) {
					cad.setPosicao(cod2);
					cad.setNome(textInput("Digite o nome atualizado: "));
					cad.setDataNascimento(data.data());
					textInput("");
					cad.setCpf(cpf.validarCPF());
					textInput("");
					cad.setCelular(celular.formatarCelular());
					textInput("");
					cadastroEmArquivo.add(cad);
					salvar.add(cad.toString());
					System.out.println("Atualização realizada");
				} else {
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

		} catch (IOException e) {
			System.out.println("Erro ao tentar atualizar.");
		}		
	}
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();

	}
}
