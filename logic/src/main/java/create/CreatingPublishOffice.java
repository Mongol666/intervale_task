package create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreatingPublishOffice {
    // создание запроса на создание нового издательства
    private static final String INSERT_INTO_AUTHOR = "INSERT INTO publish_offices (name) VALUES (?)";

    public static void createPublishOffice(String publish_office_name, Connection connection) throws SQLException {
        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_AUTHOR);

        statement.setString(1, publish_office_name);
        statement.execute();
    }
}
