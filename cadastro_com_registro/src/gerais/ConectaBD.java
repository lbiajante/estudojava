package gerais;

import java.sql.Connection;
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
		if (dataSource == null) {
			createConnectionPool();
		}
		if (connection == null || connection.isClosed()) {
			connection = dataSource.getPooledConnection().getConnection();
		}
		return connection;

	}

}