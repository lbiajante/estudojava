package cadastro;

import java.util.ArrayList;
import java.util.Scanner;
import registro.*;

public class Listar {
	Util util = new Util();
	ListarRegistro listarRegistro = new ListarRegistro();
	private Scanner entrada;

	public void listarCadastros(String path) {

		System.out.println("Escolha uma opcao: ");
		System.out.println("1- Listar cadastros de pessoas");
		System.out.println("2- Listar registros de visitas");
		entrada = new Scanner(System.in);
		String opLista = entrada.next();
		if (opLista.equals("1")) {

			ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
			list = util.lerArquivo(path);
			if (list.isEmpty()) {
				System.out.println("Cadastro vazio");
			} else {
				for (Object c : list) {
					System.out.println(((CadastroEmArquivo) c).toString());
				}
			}
		}else if (opLista.equals("2")){
			listarRegistro.listarRegistros(path);
		}
	}
}
