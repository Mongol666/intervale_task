package cashing.types;

import connection.AbstractConnection;
import printed_products.type.TypeOfProduct;
import publishing_office.PublishOffice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CashTypes {
    //запрос к таблице типов
    private static final String SELECT_FROM_TYPES = "SELECT * FROM types";

    /**
     * Метод, который вытаскивает значения из таблицы издательств
     * @return {@link List} издательств
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static List<TypeOfProduct> getPublishOffices() throws SQLException, ClassNotFoundException {
        //результат работы метода
        List<TypeOfProduct> types = new ArrayList<>();

        //создание Connection-а к БД
        Connection connection = AbstractConnection.getConnection();

        //создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_TYPES);

        //создание ResultSet-а
        ResultSet set = statement.executeQuery();

        //забираем данные из таблицы, добавляя новых издательств в List
        while (set.next()) {
            int id = set.getInt(1);
            switch (id) {
                case 1:
                    types.add(TypeOfProduct.BOOK);
                    break;
                case 2:
                    types.add(TypeOfProduct.MAGAZINE);
                    break;
                case 3:
                    types.add(TypeOfProduct.NEWSPAPER);
            }
        }

        close(connection, statement, set);

        return types;
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
