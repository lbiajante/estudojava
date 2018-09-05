package cadastro;

import java.util.ArrayList;

public class Listar {
	Util util = new Util();

	public void listarCadastros(String path) {

		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();		
		list = util.lerArquivo(path);
		if (list.isEmpty()) {
			System.out.println("Cadastro vazio");
		} else {
			for (Object c : list) {
				System.out.println(((CadastroEmArquivo) c).toString());				
			}
			
		}
	}

}
