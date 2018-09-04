package cadastro;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Listar {
	Util util = new Util();

	public void listarCadastros(String path) {
		
		
		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
		list = util.lerArquivo(path, 1);
		System.out.println(list.size());
		
		for (Object c: list){
		
		System.out.println(((CadastroEmArquivo)c).toString());
		System.out.println(((CadastroEmArquivo)c).getPosicao());
		// System.out.println(cad);
		}
	}

}
