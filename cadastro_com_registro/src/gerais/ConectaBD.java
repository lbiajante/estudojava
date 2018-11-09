package gerais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class ConectaBD {

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

		try {
			if (dataSource == null) {
				createConnectionPool();
			}
			if (connection == null || connection.isClosed()) {
				connection = dataSource.getPooledConnection().getConnection();
			}
		} catch (SQLException e) {
			System.out.println("Erro na conexao - - - " + e.getMessage());
		
			
		}
		return connection;

	}

	public static Connection getConn() throws SQLException {
		connection = null;
		try {
			// Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/database", "postgres",
					"root");
			// } catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} catch (SQLException esql) {
			esql.printStackTrace();
		}

		return connection;

	}

}