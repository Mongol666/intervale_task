package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/catalog";
    private static final String USER = "mongol";
    private static final String PASSWORD = "Gurmanidze01";
    private static final String DRIVER = "org.postgresql.Driver";

    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
