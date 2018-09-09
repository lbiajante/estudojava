package registro;

import java.util.ArrayList;

public class ListarRegistro {
	UtilRegistro util = new UtilRegistro();

	public void listarRegistros(String path) {

		ArrayList<RegistroEmArquivo> list = new ArrayList<RegistroEmArquivo>();		
		
		list = util.lerArquivo(util.gerarArquivo(path));
		if (list.isEmpty()) {
			System.out.println("Cadastro vazio");
		} else {
			for (Object c : list) {
				System.out.println(((RegistroEmArquivo) c).toString());				
			}			
		}
	}
}
