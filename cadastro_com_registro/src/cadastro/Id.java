package cadastro;

import java.util.ArrayList;

public final class Id {	
	private final String id;
	Util util = new Util();
	
	public Id(int id) {	
		this.id = String.format("%06d", id).trim();			
	}			
	
	public String getId() {
		return id;
	}	
	
	/*public void listarCadastros(String path) {
		ArrayList<CadastroEmArquivo> list = new ArrayList<CadastroEmArquivo>();
		list = util.lerArquivo(path, 1);
		System.out.println(list.size());
		
		for (Object c: list){
		
		System.out.println(((CadastroEmArquivo)c).toString());
		System.out.println(((CadastroEmArquivo)c).getPosicao());
		// System.out.println(cad);
		}
	}*/
}