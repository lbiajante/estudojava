package cadastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import registro.CadastrarRegistro;

public class Atualizar {
	Scanner entrada = new Scanner(System.in);

	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}

	public void atualizarCadastro() {
		
		CadastroPessoa cad = new CadastroPessoa();
		Conexao con = new Conexao();
		ValidaData data = new ValidaData();
		ValidaCPF cpf = new ValidaCPF();
		ValidaCelular celular = new ValidaCelular();
		ValidaStrings string = new ValidaStrings();
		boolean confere2 = true;
		boolean confere = true;
		String confereCod = null;
		String codigo = null;

		System.out.println("Digite uma opcao: ");
		System.out.println("1- Atualizar os dados de cadastro de pessoas");
		System.out
		.println("2- Adicionar registro de visita referente a um cadastro");
		String opAtualizar = entrada.nextLine();

		if (opAtualizar.equalsIgnoreCase("1")) {
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
								confereCod = String.format("%06d", cod).trim();
								
								String sql = "SELECT * FROM cadastro_de_pessoas";
								try {
									PreparedStatement ps = con.conexao().prepareStatement(sql);
									ResultSet rs = ps.executeQuery();
									
									while(rs.next()){							    
									    cad.setPosicao(rs.getString("id"));
										if (cad.getPosicao().equals(confereCod)) {
											
											String label = "Digite o nome atualizado";
											cad.setNome(string.texto(
													textInput(label), label));
											cad.setDataNascimento(data.data());
											cad.setCpf(cpf.validarCPF());
											label = "Digite o nome da empresa atualizado";
											cad.setEmpresa(string.texto(
													textInput(label), label));
											label = "Digite a area de atuacao";
											cad.setAreaDeAtuacao(string.texto(
													textInput(label), label));
											cad.setCelular(celular.formatarCelular());
											
											String sql2 = "UPDATE cadastro_de_pessoas SET "
													+ "(id, nome_pessoa, data_nasc, cpf, celular, empresa, area_atuação) values" 
													+ "( '" + cad.getPosicao() + "' , '" 
													+ cad.getNome() + "' , '"
													+ cad.getDataNascimento() + "' , '"
													+ cad.getCpf() + "' , '" + cad.getCelular()
													+ "' , '" + cad.getEmpresa() + "' , '"
													+ cad.getAreaDeAtuacao()+"' );";
											try {
												PreparedStatement ps2 = con.conexao().prepareStatement(sql2);
												System.out.println(sql2);
												ps2.execute();						

											} catch (SQLException e) {
												e.printStackTrace();
											}

											System.out.println("Cadastro adicionado!");
											System.out.println(cad.toString());
											
											confere = false;
											confere2 = false;
										} else {
											System.out
											.println("Não existe cadastro com esse ID");
										}										
									    	
											codigo = entrada.next();
											confere = true;									
											break;
									
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}			
							
							}	
						}
					}
				} catch (NumberFormatException e) {
					System.out.printf("Voce nao digitou um numero inteiro!\n");
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

//						CadastroPessoa pessoa = em.find(CadastroPessoa.class,
//								confereCod);

//						if (pessoa != null) {
//							System.out.println("Cadastro localizado");
//							conf = false;
//							CadastrarRegistro cadReg = new CadastrarRegistro();
//							cadReg.cadastrar(pessoa.getPosicao(), pessoa.getNome());
//						} else {
//							System.out.println("Cadastro nao localizado");
//							conf = true;
						//}
					}
				}
			}
		}
	}
}
