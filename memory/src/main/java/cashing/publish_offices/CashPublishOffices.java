package cashing.publish_offices;

import connection.AbstractDataSource;
import publishing_office.PublishOffice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CashPublishOffices {
    //запрос к таблице издательств
    private static final String SELECT_FROM_PUBLISH_OFFICES = "SELECT * FROM publish_offices";

    /**
     * Метод, который вытаскивает значения из таблицы издательств
     * @return {@link List} издательств
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static List<PublishOffice> getPublishOffices() throws SQLException, ClassNotFoundException, IOException {
        //результат работы метода
        List<PublishOffice> offices = new ArrayList<>();

        //создание Connection-а к БД
        Connection connection = AbstractDataSource.getInstance().getConnection();

        //создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_PUBLISH_OFFICES);

        //создание ResultSet-а
        ResultSet set = statement.executeQuery();

        //забираем данные из таблицы, добавляя новых издательств в List
        while (set.next()) {
            int id = set.getInt(1);
            String name = set.getString(2);
            offices.add(new PublishOffice(id, name));
        }

        close(connection, statement, set);

        return offices;
    }

    /**
     * Закрытие ресурсов:
     * @param connection тип {@link Connection}
     * @param statement тип {@link PreparedStatement}
     * @param set тип {@link ResultSet}
     * @throws SQLException
     */
    private static void close(Connection connection, PreparedStatement statement, ResultSet set) throws SQLException {
        statement.close();
        set.close();
        connection.close();
    }
}
