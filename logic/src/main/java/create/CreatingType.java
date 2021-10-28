package create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatingType {
    // создание запроса на создание нового типа
    private static final String INSERT_INTO_TYPE = "INSERT INTO types (name) VALUES (?)";

    public static void createType(String type_name, Connection connection) throws SQLException {
        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_TYPE);

        statement.setString(1, type_name);
        statement.execute();
    }
}
