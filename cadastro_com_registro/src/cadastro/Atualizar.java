package cadastro;

import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import registro.*;

public class Atualizar {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarCadastro(String path) {
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		Util util = new Util();
		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
		CadastrarRegistro cadReg = new CadastrarRegistro();
		CadastroEmArquivo cad = new CadastroEmArquivo();
		boolean confere2 = true;
		boolean confere = true;
		String confereCod = null;
		String codigo = null;

		list = util.lerArquivo(path);
		System.out.println("Digite uma opcao: ");
		System.out.println("1- Atualizar os dados de cadastro de pessoas");
		System.out.println("2- Adicionar nova visita referente a um cadastro");
		String opAtualizar = entrada.nextLine();

		if (list.isEmpty()) {
			System.out.println("Cadastro vazio, sem itens para atualizar");
		} else {
			if (opAtualizar.equalsIgnoreCase("1")) {
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
							codigo = textInput("Digite um ID para ser atualizado ou 's' para sair");
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
									confereCod = String.format("%06d", cod)
											.trim();
									for (Object c : list) {
										if (confereCod
												.equals(((CadastroEmArquivo) c)
														.getPosicao())) {
											System.out
											.println("Cadastro localizado");
											cad.setPosicao(((CadastroEmArquivo) c)
													.getPosicao());
											String label = "Digite o nome atualizado";
											cad.setNome(string.texto(
													textInput(label), label));
											cad.setDataNascimento(data.data());
											cad.setCpf(cpf.validarCPF());
											label = "Digite o nome da empresa atualizado";
											cad.setEmpresa(string.texto(
													textInput(label), label));
											label = "Digite a area de atuacao atualizado";
											cad.setAreaDeAtuacao(string.texto(
													textInput(label), label));
											cad.setCelular(celular
													.formatarCelular());
											System.out
											.println("Cadastro adicionado!");
											System.out.println(cad.toString());

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
									new File(nomeArquivo).renameTo(new File(
											path));

								} catch (Exception e) {
									System.out
									.println("Falha ao atualizar cadastro");
									return;
								}
							}
						}
					} catch (NumberFormatException e) {
						System.out
						.printf("Voce nao digitou um numero inteiro!\n");
						codigo = textInput("Digite um numero inteiro");
						confere2 = true;
					}
				} while (confere2);

			} else if (opAtualizar.equals("2")) {
				boolean conf = true;
				while (conf) {
					codigo = textInput("Digite o ID do cadastro da pessoa \n para incluir novo registro ou 's' para sair");
					if (codigo.trim().equalsIgnoreCase("s")) {
						conf = false;
					} else {
						int cod = Integer.parseInt(codigo.trim());
						if (cod <= 0) {
							System.out
							.println("O codigo precisa ser maior que zero");
							conf = true;
						} else {
							confereCod = String.format("%06d", cod).trim();
							int count = 0;

							for (Object c : list) {
								if (confereCod.equals(((CadastroEmArquivo) c)
										.getPosicao())) {
									count = 1;
									System.out.println("Cadastro localizado");
									cadReg.cadastrar(path,
											(((CadastroEmArquivo) c)
													.getPosicao()),
													(((CadastroEmArquivo) c).getNome()));
								}
								break;
							}
							if (count == 0) {
								System.out.println("Cadastro não localizado");
							}
							conf = false;
						}
					}
				}
			}
		}
	}
}
