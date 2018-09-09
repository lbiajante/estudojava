package registro;
//
//import java.util.Scanner;
//
//public class MainRegistro {
//	public static void main(String[] args) {
//
//		boolean execute = true;
//		String path;
//		UtilRegistro util = new UtilRegistro();
//		CadastrarRegistro cadastrar = new CadastrarRegistro();	
//		ListarRegistro listar = new ListarRegistro();
//		Scanner entrada;
//
//		System.out.println("BEM VINDO AO REGISTRO DE LUGARES VISITADOS");
//		System.out.println("");
//		path = util.gerarArquivo();		
//
//		while (execute) {
//			entrada = new Scanner(System.in);
//			System.out.println("");
//			System.out.println("-------------------------------------------");
//			System.out.println("Digite o numero da opcao selecionada:");
//			System.out.println("1 - Novo registro");
//			System.out.println("2 - Listar registros");		
//			System.out.println("3 - Sair");
//			System.out.println("-------------------------------------------");
//			String opcao = entrada.next();
//			if (opcao.equals("1")) {
//				System.out.println("");
//				cadastrar.cadastrar(path);
//				execute =  true;
//			} else if (opcao.equals("2")) {
//				listar.listarCadastros(path);
//				execute =  true;
//			} else if (opcao.equals("3")) {				
//				System.out.println("Programa de cadastro finalizado!");
//				System.out.println("");
//				execute = false;
//			} else {
//				System.out.println("Opcao Invalida!");
//			}
//		}
//	}
//}
