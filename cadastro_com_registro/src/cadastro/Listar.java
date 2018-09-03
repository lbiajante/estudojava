package cadastro;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Listar {
	Util util = new Util();

	public void listarCadastros(String path) {

		ArrayList<Object> cad  = Util.lerArquivo(path);
		for (Object c : cad){
			System.out.println(((CadastroEmArquivo)c).toString());
			System.out.printf(((CadastroEmArquivo)c).getNome());
			
		}		
	}
}
