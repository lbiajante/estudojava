package cadastro;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Remover {

	
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void removerCadastro(String path) {
		Util util = new Util();
		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
		CadastroEmArquivo cad = new CadastroEmArquivo();
		boolean confere2 = true;
		boolean confere = true;
		String confereCod = null;
		String codigo = null;
		list = util.lerArquivo(path);

		if (list.isEmpty()) {
			System.out.println("Cadastro vazio, sem itens para remover");
		} else {
			String nomeArquivo = path;
			String[] splitted = nomeArquivo.split("\\.");
			nomeArquivo = splitted[0];
			nomeArquivo = nomeArquivo + "2.txt";
			try {
				FileWriter criadorDeArquivo = new FileWriter(nomeArquivo);
				criadorDeArquivo.flush();
				criadorDeArquivo.close();
			} catch (IOException e) {
				System.out.println("Erro na criacao do arquivo");
			}
			do {
				try {
					while (confere) {
						codigo = textInput("Digite um ID para ser removido ou 's' para sair");
						if (codigo.trim().equalsIgnoreCase("s")) {
							confere = false;
							confere2 = false;
						} else {
							int cod = Integer.parseInt(codigo.trim());
							if (cod <= 0) {
								System.out
										.println("O codigo precisa ser maior que zero");
								confere = true;
								confere2 = true;
							} else {
								confereCod = String.format("%06d", cod).trim();
								for (Object c : list) {
									if (confereCod
											.equals(((CadastroEmArquivo) c)
													.getPosicao())) {
										System.out
												.println("Cadastro removido");
										confere = false;
										confere2 = false;
									} else {

										cad.setPosicao(((CadastroEmArquivo) c)
												.getPosicao());
										cad.setNome(((CadastroEmArquivo) c)
												.getNome());
										cad.setDataNascimento(((CadastroEmArquivo) c)
												.getDataNascimento());
										cad.setCpf(((CadastroEmArquivo) c)
												.getCpf());
										cad.setEmpresa(((CadastroEmArquivo) c)
												.getEmpresa());
										cad.setAreaDeAtuacao(((CadastroEmArquivo) c)
												.getAreaDeAtuacao());
										cad.setCelular(((CadastroEmArquivo) c)
												.getCelular());
										try {
											FileOutputStream arq = new FileOutputStream(
													nomeArquivo, true);
											ObjectOutputStream objOutput = new ObjectOutputStream(
													arq);
											objOutput.writeObject(cad);
											objOutput.flush();
											objOutput.close();	
											arq.flush();
											arq.close();											
										} catch (IOException erro) {
											erro.printStackTrace();
										} 
									}
								}
							}
							try {
								File f = new File(path);
								f.delete();
								new File(nomeArquivo).renameTo(new File(path));

							} catch (Exception e) {
								System.out
										.println("Falha ao atualizar cadastro");
								return;
							}
						}
					}
				} catch (NumberFormatException e) {
					System.out.printf("Voce nao digitou um numero inteiro!\n");
					codigo = textInput("Digite um numero inteiro");
					confere2 = true;
				}
			} while (confere2);

		}
	}
}
