package gerais;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGConnectionPoolDataSource;

//classe de abertura de conexão com o BD
public class ConectaBD {
	// public static Connection getConnection() {
	//
	//
	// //constantes utilizadas para conexão
	// String driver = "org.postgresql.Driver";
	// String user = "postgres";
	// String senha = "root";
	// String url = "jdbc:postgresql://localhost:5432/database";
	// Connection con = null;
	// try {
	// Class.forName(driver);
	// con = DriverManager.getConnection(url, user, senha);
	//
	// } catch (ClassNotFoundException ex) {
	// System.err.print(ex.getMessage());
	// } catch (SQLException e) {
	// System.err.print(e.getMessage());
	// }return con;
	// }
	// }
	// public class Database {

	private static Connection connection;
	private static javax.sql.ConnectionPoolDataSource dataSource;

	private static void createConnectionPool() {

		PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();
		pool.setUrl("jdbc:postgresql://localhost:5432/database");
		pool.setUser("postgres");
		pool.setPassword("root");
		dataSource = pool;
	}

	public static Connection getConnection() throws SQLException {

		if (dataSource == null) {
			createConnectionPool();
		}

		if (connection == null || connection.isClosed()) {
			connection = dataSource.getPooledConnection().getConnection();
		}

		return connection;

	}

}