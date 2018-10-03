package utilitarias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//classe de abertura de conexão com o BD
public class ConexaoBD {
	public static Connection conexao() {
		//constantes utilizadas para conexão
		String driver = "org.postgresql.Driver";
		String user = "postgres";
		String senha = "root";
		String url = "jdbc:postgresql://localhost:5432/database";
		Connection con = null;
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, senha);
			
		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}return con;
	}
}