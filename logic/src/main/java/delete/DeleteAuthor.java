package delete;

import connection.AbstractConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAuthor {
    // создание запроса на получение id автора по его имени
    // language=SQL
    private static final String GET_ID_BY_AUTHOR_NAME = "SELECT id FROM authors WHERE name = ?";

    // создание запроса на изменение id автора в таблице печатной продукции
    // language=SQL
    private static final String UPDATE_AUTHOR_ID_IN_PRINTED_PRODUCT = "UPDATE printed_products SET author = 0 WHERE author = ?";

    // создание запроса на удаление автора из таблицы авторов
    // language=SQL
    private static final String DELETE_AUTHOR_BY_NAME = "DELETE FROM authors WHERE name = ?";

    /**
     * Метод, который удаляет все данные об авторе из всех таблиц
     * @param author_name имя автора
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void deleteAuthor(String author_name) throws SQLException, ClassNotFoundException {
        // создание Connection-а
        Connection connection = AbstractConnection.getConnection();

        // создание PreparedStatement-а для получения id автора по имени
        PreparedStatement statementForGetAuthorID = connection.prepareStatement(GET_ID_BY_AUTHOR_NAME);

        // ID автора
        int author_id;

        if (statementForGetAuthorID.executeQuery().next()) {
            author_id = statementForGetAuthorID.executeQuery().getInt("id");
        } else {
            throw new SQLException("Автор " + author_name + " уже удалён или не существует.");
        }

        // создание PreparedStatement-а для изменения id автора в таблице печатной продукции
        PreparedStatement statementForUpdateAuthorID = connection.prepareStatement(UPDATE_AUTHOR_ID_IN_PRINTED_PRODUCT);
        statementForUpdateAuthorID.setInt(1, author_id);
        statementForUpdateAuthorID.execute();

        // создание PreparedStatement-а для удаления автора из таблицы авторов
        PreparedStatement statementForDeleteAuthor = connection.prepareStatement(DELETE_AUTHOR_BY_NAME);
        statementForDeleteAuthor.setString(1, author_name);
        statementForDeleteAuthor.execute();

        closeResources(connection, statementForGetAuthorID, statementForUpdateAuthorID, statementForDeleteAuthor);
    }

    /**
     * Метод, который закрывает все ресурсы
     * @param connection {@link Connection}
     * @param statementForGetAuthorID {@link PreparedStatement}, который находит ID автора по имени
     * @param statementForUpdateAuthorID {@link PreparedStatement}, который устанавливает ID автора в таблице печатной продукции на 0
     * @param statementForDeleteAuthor {@link PreparedStatement}, который удаляет автора из таблицы авторов
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
