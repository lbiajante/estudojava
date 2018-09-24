package local;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import utilitarias.Conexao;
import utilitarias.ValidaId;
import utilitarias.ValidaStrings;

public class CadastrarLocal {
	Conexao con = new Conexao();
	Local cadLocal = new Local();
	ValidaStrings string = new ValidaStrings();
	ValidaId validaId = new ValidaId();
	Scanner entrada = new Scanner(System.in);

	public void cadastrarLocal(){
		
		System.out.println("Cadastro de lugares");		
		String table = "local";
		String label = "Digite o ID do lugar";
		cadLocal.setId(validaId.verificaID(textInput(label), table));		
		 label = "Digite o lugar";
		cadLocal.setLugar(string.texto(textInput(label), label));
		
		String sql = "INSERT INTO local "
				+ "(id, lugar) values"
				+ "( '" + cadLocal.getId()+"' , '" + cadLocal.getLugar()+ "' );";
		try {
			PreparedStatement ps = con.conexao().prepareStatement(sql);				
			ps.execute();
			ps.close();
			System.out.println("Lugar adicionado!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private String textInput(String label) {
		System.out.println(label);
		return entrada.nextLine();
	}
}
