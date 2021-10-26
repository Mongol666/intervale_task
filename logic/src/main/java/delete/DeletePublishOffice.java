package delete;

import connection.AbstractDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePublishOffice {
    // создание запроса на получение id издательства по его название
    // language=SQL
    private static final String GET_ID_BY_PUBLISH_OFFICE_NAME = "SELECT id FROM publish_offices WHERE name = ?";

    // создание запроса на изменение id издательства в таблице печатной продукции
    // language=SQL
    private static final String UPDATE_PUBLISH_OFFICE_ID_IN_PRINTED_PRODUCT = "UPDATE printed_products SET publish_office = 0 WHERE publish_office = ?";

    // создание запроса на удаление издательства из таблицы авторов
    // language=SQL
    private static final String DELETE_PUBLISH_OFFICE_BY_NAME = "DELETE FROM publish_offices WHERE name = ?";

    /**
     * Метод, который удаляет все данные об издательстве из всех таблиц
     * @param publish_office_name название издательства
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void deletePublishOffice(String publish_office_name) throws SQLException, ClassNotFoundException, IOException {
        // создание Connection-а
        Connection connection = AbstractDataSource.getInstance().getConnection();

        // создание PreparedStatement-а для получения id издательства по имени
        PreparedStatement statementForGetPublishOfficeID = connection.prepareStatement(GET_ID_BY_PUBLISH_OFFICE_NAME);

        // ID автора
        int publish_office_id;

        if (statementForGetPublishOfficeID.executeQuery().next()) {
            publish_office_id = statementForGetPublishOfficeID.executeQuery().getInt("id");
        } else {
            throw new SQLException("Издательство " + publish_office_name + " уже удалено или не существует.");
        }

        // создание PreparedStatement-а для изменения id издательства в таблице печатной продукции
        PreparedStatement statementForUpdatePublishOfficeID = connection.prepareStatement(UPDATE_PUBLISH_OFFICE_ID_IN_PRINTED_PRODUCT);
        statementForUpdatePublishOfficeID.setInt(1, publish_office_id);
        statementForUpdatePublishOfficeID.execute();

        // создание PreparedStatement-а для удаления автора из таблицы авторов
        PreparedStatement statementForDeletePublishOffice = connection.prepareStatement(DELETE_PUBLISH_OFFICE_BY_NAME);
        statementForDeletePublishOffice.setString(1, publish_office_name);
        statementForDeletePublishOffice.execute();

        closeResources(connection, statementForGetPublishOfficeID, statementForUpdatePublishOfficeID, statementForDeletePublishOffice);
    }

    /**
     * Метод, который закрывает все ресурсы
     * @param connection {@link Connection}
     * @param statementForGetAuthorID {@link PreparedStatement}, который находит ID издательства по названию
     * @param statementForUpdateAuthorID {@link PreparedStatement}, который устанавливает ID издательства в таблице печатной продукции на 0
     * @param statementForDeleteAuthor {@link PreparedStatement}, который удаляет издательство из таблицы издательств
     * @throws SQLException: обрабатывается в модуле app
     */
    private static void closeResources(Connection connection,
                                       PreparedStatement statementForGetAuthorID,
                                       PreparedStatement statementForUpdateAuthorID,
                                       PreparedStatement statementForDeleteAuthor) throws SQLException {
        connection.close();
        statementForGetAuthorID.close();
        statementForUpdateAuthorID.close();
        statementForDeleteAuthor.close();
    }
}
