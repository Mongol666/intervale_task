package update;

import connection.AbstractDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAuthor {
    // создание запроса на изменение имени автора
    private static final String UPDATE_AUTHOR_NAME = "UPDATE authors SET name = ? WHERE name = ?";

    /**
     * Метод по изменению имени автора
     * @param old_author_name  старое имя автора
     * @param new_author_name  новое имя автора
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void updateAuthor(String old_author_name, String new_author_name) throws SQLException, ClassNotFoundException, IOException {
        // создание Connection-а
        Connection connection = AbstractDataSource.getInstance().getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR_NAME);

        statement.setString(1, new_author_name);
        statement.setString(2, old_author_name);
        statement.execute();

        closeResources(connection, statement);
    }

    /**
     * Закрытие ресурсов
     * @param connection {@link Connection}
     * @param statement {@link PreparedStatement}
     * @throws SQLException
     */
    private static void closeResources(Connection connection, PreparedStatement statement) throws SQLException {
        connection.close();
        statement.close();
    }
}
