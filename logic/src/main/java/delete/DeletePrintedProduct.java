package delete;

import connection.AbstractConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePrintedProduct {
    // создание запроса на удаление одного кортежа из таблицы печатной продукции
    private static final String DELETE_PRINTED_PRODUCT = "DELETE FROM printed_products WHERE name = ?";

    public static void deletePrintedProduct(String printed_product_name) throws SQLException, ClassNotFoundException {
        // создание Connection-а
        Connection connection = AbstractConnection.getConnection();

        // создание PreparedStatement-а
        PreparedStatement statement = connection.prepareStatement(DELETE_PRINTED_PRODUCT);

        statement.setString(1, printed_product_name);
        statement.execute();
    }
}
