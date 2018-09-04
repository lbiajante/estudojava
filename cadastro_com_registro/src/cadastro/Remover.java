package cadastro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Remover {

	private Util util = new Util();
	private Listar listar = new Listar();
	Scanner entrada = new Scanner(System.in);
	
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerCadastro(String path) {
		
		CadastroEmArquivo cad = new CadastroEmArquivo();
		ArrayList<CadastroEmArquivo> lista = new ArrayList<CadastroEmArquivo>();
		FileInputStream path1 = null;

		String ID = String.format("%06d", (util.verificaCodigo(textInput("Digite o numero do ID que será removido"))));
		System.out.println(ID);
		boolean confere = true;
		try {
			path1 = new FileInputStream(path);
			while (true) {
				ObjectInputStream objInput = new ObjectInputStream(path1);
				cad = (CadastroEmArquivo) objInput.readObject();	
				if (ID.equals(cad.getPosicao())){
					System.out.println(cad.getPosicao());
					
				}else {
					lista.add (cad);
					}				
				}
						
		} catch (EOFException eof) {
			return ;
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
		
		try {
			FileOutputStream arq = new FileOutputStream(path);
			ObjectOutputStream objOutput = new ObjectOutputStream(arq);
			
			for (Object c : lista){			
			objOutput.writeObject(lista);
			objOutput.flush();
			objOutput.close();
			}
		} catch (IOException erro) {
			erro.printStackTrace();
		}
		//return (cad);
	}
		
		
		/*File arq = new File(path);
		String posicao;

		try {
			FileReader fr = new FileReader(arq);
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			ArrayList<String> salvar = new ArrayList<String>();
			int j = 0;

			if (linha == null) {
				System.out
				.println("Arquivo de cadastro vazio, nao contem itens a serem apagados");
			} else {
				listar.listarCadastros(path);

				boolean confere = true;
				while (confere) {
					System.out
					.println("Indique o numero de cadastro para remover: ");
					String cod = entrada.nextLine();
					int cod2 = util.verificaCodigo(cod);

					if (cod2 == 0) {
						confere = true;
					} else {
						int verificaSeExiste = 0;
						posicao = (String.format("%03d", cod2)).trim();

						while (linha != null) {
							if (linha.substring(0, 12).contains(posicao) == false) {
								j++;
								String posicaoAntiga = linha.substring(0, 12);
								String posicaoNova = "Posicao: "
										+ String.format("%03d", j).trim();
								String newLine = linha.replace(posicaoAntiga,
										posicaoNova);
								salvar.add(newLine);
							} else {
								verificaSeExiste = 1;
							}
							linha = br.readLine();
						}
						confere = false;
						br.close();
						fr.close();

						FileWriter fw = new FileWriter(arq);
						BufferedWriter bw = new BufferedWriter(fw);
						for (int i = 0; i < salvar.size(); i++) {
							bw.write(salvar.get(i));
							bw.newLine();
						}
						bw.flush();
						fw.flush();
						bw.close();
						fw.close();

						if (verificaSeExiste == 1) {
							listar.listarCadastros(path);
							System.out
							.printf("\nCadastro da "
									+ posicao.toLowerCase()
									+ " removido com sucesso. \nLista renumerada.");
						} else {
							System.out
							.printf("\nNumero de cadastro nao existe.\nCadastro nao removido.");
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao tentar remover.");
		}*/

	}

