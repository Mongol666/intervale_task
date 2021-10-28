package connection;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class AbstractDataSource implements DataSource {
    private final String url;
    private final String user;
    private final String password;

    private static volatile AbstractDataSource instance;

    private AbstractDataSource() throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("connection.properties"));

        this.url = properties.getProperty("url");
        this.user = properties.getProperty("user");
        this.password = properties.getProperty("password");

        Class.forName(properties.getProperty("driver"));
    }

    public static AbstractDataSource getInstance() throws IOException, ClassNotFoundException {
        if (instance == null) {
            synchronized (AbstractDataSource.class) {
                if (instance == null) {
                    instance = new AbstractDataSource();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new SQLException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new SQLException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new SQLException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new SQLException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new SQLException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException();
    }
}
