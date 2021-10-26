package cashing.product;

import authors.Author;
import connection.AbstractDataSource;
import printed_products.printed_product.PrintedProduct;
import printed_products.type.TypeOfProduct;
import publishing_office.PublishOffice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class CashProducts {
    // запрос к таблице печатной продукции
    private static final String SELECT_FROM_PRINTED_PRODUCT =
            "SELECT  pp.id,\n" +
                    "pp.name,\n" +
                    "a.id as author_id,\n" +
                    "a.name as author_name,\n" +
                    "po.id as publish_office_id,\n" +
                    "po.name as publish_office_name,\n" +
                    "pp.data as date,\n" +
                    "t.name as type_name\n" +
                    "FROM printed_products pp\n" +
                    "INNER JOIN authors a on a.id = pp.author\n" +
                    "INNER JOIN publish_offices po on pp.publish_office = po.id\n" +
                    "INNER JOIN types t on pp.type = t.id";

    public static List<PrintedProduct> getPrintedProducts() throws SQLException, ClassNotFoundException, IOException {
        // результат рабоыт метода
        List<PrintedProduct> printedProducts = new ArrayList<>();

        // создание Connection-а к БД
        Connection connection = AbstractDataSource.getInstance().getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(SELECT_FROM_PRINTED_PRODUCT);

        // создание ResultSet-а
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            int printed_product_id = set.getInt(1);
            String printed_product_name = set.getString(2);

            int author_id = set.getInt(3);
            String author_name = set.getString(4);

            int publish_office_id = set.getInt(5);
            String publish_office_name = set.getString(6);

            LocalDate date = set.getDate(7).toLocalDate();

            String type_name = set.getString(8);
            TypeOfProduct type = getType(type_name);

            printedProducts.add(new PrintedProduct(
                    printed_product_id,
                    printed_product_name,
                    new Author(author_id, author_name),
                    type,
                    date,
                    new PublishOffice(publish_office_id, publish_office_name)));
        }

        close(connection, statement, set);

        return printedProducts;
    }

    private static TypeOfProduct getType(String type_name) throws SQLException {
        TypeOfProduct type;
        switch (type_name) {
            case "Книга":
                type = TypeOfProduct.BOOK;
                break;
            case "Журнал":
                type = TypeOfProduct.MAGAZINE;
                break;
            case "Газета":
                type = TypeOfProduct.NEWSPAPER;
                break;
            default:
                throw new SQLException("Такого типа печтаной продукции нет: " + type_name);
        }
        return type;
    }

    private static void close(Connection connection, PreparedStatement statement, ResultSet set) throws SQLException {
        connection.close();
        statement.close();
        set.close();
    }

}
