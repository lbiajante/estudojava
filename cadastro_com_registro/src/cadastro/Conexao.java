package cadastro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conexao() {
		String driver = "org.postgresql.Driver";
		String user = "postgres";
		String senha = "root";
		String url = "jdbc:postgresql://localhost:5432/database";
		Connection con = null;
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user, senha);
			//System.out.println("Conexão realizada com sucesso.");
		} catch (ClassNotFoundException ex) {
			System.err.print(ex.getMessage());
		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}return con;
	}
}
