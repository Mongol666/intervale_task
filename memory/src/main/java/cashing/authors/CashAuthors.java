package cashing.authors;

import authors.Author;
import connection.AbstractConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CashAuthors {

    //запрос к таблице авторов
    private static final String SELECT_FROM_AUTHORS = "SELECT * FROM authors";

    /**
     * Метод, который вытаскивает значения из таблицы авторов
     * @return {@link List} авторов
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static List<Author> getAuthorsFromTable() throws SQLException, ClassNotFoundException {
        //результат работы метода
        List<Author> authors = new ArrayList<>();

        //создание Connection-а к БД
        Connection connection = AbstractConnection.getConnection();

        //создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_AUTHORS);

        //создание ResultSet-а
        ResultSet set = statement.executeQuery();

        //забираем данные из таблицы, добавляя новых авторов в List
        while (set.next()) {
            int id = set.getInt(1);
            String name = set.getString(2);
            authors.add(new Author(id, name));
        }

        close(connection, statement, set);

        return authors;
    }

    /**
     * Закрытие ресурсов:
     * @param connection тип {@link Connection}
     * @param statement тип {@link PreparedStatement}
     * @param set тип {@link ResultSet}
     * @throws SQLException, которое будет обрабатываться в модуле app
     */
    private static void close(Connection connection, PreparedStatement statement, ResultSet set) throws SQLException {
        statement.close();
        set.close();
        connection.close();
    }
}
