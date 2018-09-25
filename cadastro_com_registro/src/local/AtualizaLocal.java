//package local;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//import utilitarias.Conexao;
//import utilitarias.ValidaId;
//import utilitarias.ValidaStrings;
//
//public class AtualizaLocal {
//	Scanner entrada = new Scanner(System.in);
//
//	private String textInput(String label) {
//		System.out.println(label);
//		return entrada.nextLine();
//	}
//
//	public void atualizarLocal() {
//
//		Local local = new Local();
//		ValidaId validaId = new ValidaId();
//		ValidaStrings string = new ValidaStrings();
//		boolean confere2 = true;
//		boolean confere = true;
//		boolean existe = false;
//		String input = null;
//
//		System.out.println("Atualizar Local");
//		do {
//			try {
//				while (confere) {
//					input = textInput("Digite um ID para ser atualizado ou 's' para sair");
//					if (input.trim().equalsIgnoreCase("s")) {
//
//						confere = false;
//						confere2 = false;
//					} else {
//						input = validaId.confereID(input);
//
//						String sql = "SELECT * FROM local";
//						try {
//							PreparedStatement ps = Conexao.conexao()
//									.prepareStatement(sql);
//							ResultSet rs = ps.executeQuery();
//							while (rs.next()) {
//								local.setLugar(rs.getString("lugar"));
//								if (local.getLugar().equals(input)) {
//									existe = true;
//									break;
//								}
//							}
//							ps.close();
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//
//						if (existe) {
//							String label = "Digite o lugar atualizado";
//							local.setLugar(string
//									.texto(textInput(label), label));
//
//							String sql2 = "UPDATE local SET lugar = '"
//									+ local.getLugar() + "';";
//							System.out.println(sql2);
//							try {
//								PreparedStatement ps2 = Conexao.conexao()
//										.prepareStatement(sql2);
//								ps2.execute();
//								ps2.close();
//
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//							confere = false;
//							confere2 = false;
//
//						} else if (existe == false) {
//							System.out
//									.println("Nao existe local com esse ID para ser atualizado");
//							confere = true;
//							confere2 = true;
//						}
//					}
//				}
//			} catch (NumberFormatException e) {
//				System.out.printf("Voce nao digitou um numero inteiro!\n");
//				input = textInput("Digite um numero inteiro");
//				confere2 = true;
//			}
//		} while (confere2);
//	}
//
//}
