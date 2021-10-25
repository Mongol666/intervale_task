package update;

import connection.AbstractConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdatePrintedProduct {
    // создание запроса на изменение названия печтаной продукции
    private static final String UPDATE_PRINTED_PRODUCT_NAME = "UPDATE printed_products SET name = ? WHERE name = ?";

    // создание запроса на изменение даты выпуска печатной продуцкии
    private static final String UPDATE_PRINTED_PRODUCT_DATE = "UPDATE printed_products SET data = ? WHERE name = ?";

    /**
     * Метод по изменению названия печатной продукции
     * @param old_printed_product_name  старое название продукции
     * @param new_printed_product_name  новое название продукции
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void updatePrintedProductName(String old_printed_product_name,
                                            String new_printed_product_name) throws SQLException, ClassNotFoundException {
        // создание Connection-а
        Connection connection = AbstractConnection.getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(UPDATE_PRINTED_PRODUCT_NAME);

        statement.setString(1, new_printed_product_name);
        statement.setString(2, old_printed_product_name);
        statement.execute();

        closeResources(connection, statement);
    }

    /**
     * Метод по изменению даты выпуска печатной продукции
     * @param printed_product_name название продукции
     * @param new_date обновлённая дата выпуска продукции
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static void updatePrintedProductDate(String printed_product_name, LocalDate new_date) throws SQLException, ClassNotFoundException {
        // создание Connection-а
        Connection connection = AbstractConnection.getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(UPDATE_PRINTED_PRODUCT_DATE);

        statement.setDate(1, Date.valueOf(new_date));
        statement.setString(2, printed_product_name);

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
