package cadastro.arquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Listar {

	 CadastroEmArquivo cad = new CadastroEmArquivo();
	 Ordenacao ordenar = new Ordenacao();
	
	public void listarCadastros() {
	//	util.contarLinhas();
		ArrayList<CadastroEmArquivo> cadastroEmArquivo = new ArrayList<CadastroEmArquivo>();
		this.importar(cadastroEmArquivo );
	}
	public void importar(ArrayList<CadastroEmArquivo> cadastroimport) {

		ordenar.ordenar();
		try {
			FileInputStream arq = new FileInputStream(cad.getNomeDoArquivo());
			InputStreamReader input = new InputStreamReader(arq);
			BufferedReader lerCadastro = new BufferedReader(input);
			String linha = lerCadastro.readLine();
			if (linha == null) {
				System.out.println("Cadastro vazio");
			}
			while (linha != null) {
				System.out.printf("%s\n", linha);
				linha = lerCadastro.readLine();
			}
			arq.close();
			lerCadastro.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.",
					e.getMessage());
		}
	}
}
