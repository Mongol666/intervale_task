package create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatingAuthor {
    // создание запроса на создание нового автора
    private static final String INSERT_INTO_AUTHOR = "INSERT INTO authors (name) VALUES (?)";

    public static void createAuthor(String author_name, Connection connection) throws SQLException {
        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_AUTHOR);

        statement.setString(1, author_name);
        statement.execute();
    }
}
