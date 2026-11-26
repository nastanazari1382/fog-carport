package persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private static HikariDataSource ds;

    private ConnectionPool() {
    }

    // Returns the single shared connection pool (singleton)
    public static ConnectionPool getInstance(String user, String password, String url, String db) {
        if (instance == null) {
            if (System.getenv("DEPLOYED") != null) {
                ds = createHikariConnectionPool(
                        System.getenv("JDBC_USER"),
                        System.getenv("JDBC_PASSWORD"),
                        System.getenv("JDBC_CONNECTION_STRING"),
                        System.getenv("JDBC_DB"));
            } else {
                ds = createHikariConnectionPool(user, password, url, db);
            }
            instance = new ConnectionPool();
        }
        return instance;
    }

    // Creates and configures the HikariCP pool
    private static HikariDataSource createHikariConnectionPool(String user, String password, String url, String db) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl(String.format(url, db));
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setPoolName("Postgresql-Pool");

        return new HikariDataSource(config);
    }

    // Returns a connection from the pool
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}