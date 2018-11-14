package gerais;

import local.ListarLocais;

public class Main {

	public static void main(String[] args) {
		Main cm = new Main();
		cm.chamaMetodo();
	}

	public void chamaMetodo() {			
//		ListarLocais l = new ListarLocais();
//		System.out.println(l.listarLocais());
//		
		MenuPrincipal mp = new MenuPrincipal();
		mp.menuPrincipal();	
	}
}
