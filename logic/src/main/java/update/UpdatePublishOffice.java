package update;

import connection.AbstractDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatePublishOffice {
    // создание запроса на изменение названия издательства
    private static final String UPDATE_PUBLISH_OFFICE_NAME = "UPDATE publish_offices SET name = ? WHERE name = ?";

    /**
     * Метод по изменению названия издательства
     * @param old_publish_office_name  старое название издательства
     * @param new_publish_office_name  новое название издательства
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void updateAuthor(String old_publish_office_name,
                                    String new_publish_office_name) throws SQLException, ClassNotFoundException, IOException {
        // создание Connection-а
        Connection connection = AbstractDataSource.getInstance().getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISH_OFFICE_NAME);

        statement.setString(1, new_publish_office_name);
        statement.setString(2, old_publish_office_name);
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
