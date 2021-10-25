package create;

import authors.Author;
import connection.AbstractConnection;
import printed_products.printed_product.PrintedProduct;
import printed_products.type.TypeOfProduct;
import publishing_office.PublishOffice;

import java.sql.*;
import java.time.LocalDate;

public class CreatingPrintedProduct {
    // создание запроса на создание нового печатного изделия
    private static final String INSERT_INTO_PRINTED_PRODUCT =
            "INSERT INTO printed_products (name, author, publish_office, type, data)" +
                    "VALUES (?, ?, ?, ?, ?)";

    // создание запроса к таблице авторов для получения id автора
    private static final String SELECT_ID_FROM_AUTHORS = "SELECT id FROM authors WHERE name = ?";

    // создание запроса к таблице издательств для получения id издательства
    private static final String SELECT_ID_FROM_PUBLISH_OFFICES = "SELECT id FROM publish_offices WHERE name = ?";

    // создание запроса к таблице типов для получения id типа
    private static final String SELECT_ID_FROM_TYPES = "SELECT id FROM types WHERE name = ?";

    public static PrintedProduct createPrintedProduct(String printed_product_name,
                                                      String author_name,
                                                      String publish_office_name,
                                                      String type_name,
                                                      LocalDate date) throws SQLException, ClassNotFoundException {
        // результат работы метода
        PrintedProduct printedProduct = new PrintedProduct();

        // создание Connection-а
        Connection connection = AbstractConnection.getConnection();

        // создание PrepareStatement-а
        PreparedStatement statement = connection.prepareStatement(INSERT_INTO_PRINTED_PRODUCT);

        // получение id для вставки в таблицу печатной продукции
        int author_id = getAuthorID(author_name, connection);
        int publish_office_id = getPublishOfficeID(publish_office_name, connection);
        int type_id = getTypeID(type_name, connection);

        if (author_id == 0) {
            CreatingAuthor.createAuthor(author_name, connection);
            author_id = getAuthorID(author_name, connection);
        }
        if (publish_office_id == 0) {
            CreatingPublishOffice.createPublishOffice(publish_office_name, connection);
            publish_office_id = getPublishOfficeID(publish_office_name, connection);
        }
        if (type_id == 0) {
            CreatingType.createType(type_name, connection);
            type_id = getTypeID(type_name, connection);
        }

        statement.setString(1, printed_product_name);
        statement.setInt(2, author_id);
        statement.setInt(3, publish_office_id);
        statement.setInt(4, type_id);
        statement.setDate(5, Date.valueOf(date));
        statement.execute();

        printedProduct.setName(printed_product_name);
        printedProduct.setAuthor(new Author(author_id, author_name));
        printedProduct.setPublishOffice(new PublishOffice(publish_office_id, publish_office_name));
        printedProduct.setType(getTypeOfPrintedProduct(type_name));
        printedProduct.setDateOfIssue(date);

        return printedProduct;
    }

    private static TypeOfProduct getTypeOfPrintedProduct(String type_name) throws SQLException {
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
                throw new SQLException("Нет такого типа продукции: " + type_name);
        }
        return type;
    }

    private static int getPublishOfficeID(String publish_office_name, Connection connection) throws SQLException {
        PreparedStatement statementForSelectPublishOfficeID = connection.prepareStatement(SELECT_ID_FROM_PUBLISH_OFFICES);
        statementForSelectPublishOfficeID.setString(1, publish_office_name);

        ResultSet set = statementForSelectPublishOfficeID.executeQuery();
        if (set.next()) {
            return set.getInt(1);
        } else {
            throw new SQLException("Издательство " + publish_office_name + " не найдено.");
        }

    }

    private static int getAuthorID(String author_name, Connection connection) throws SQLException {
        PreparedStatement statementForSelectAuthorID = connection.prepareStatement(SELECT_ID_FROM_AUTHORS);
        statementForSelectAuthorID.setString(1, author_name);

        ResultSet set = statementForSelectAuthorID.executeQuery();
        if (set.next()) {
            return set.getInt(1);
        } else {
            throw new SQLException("Автор " + author_name + " не найден.");
        }
    }

    private static int getTypeID(String type_name, Connection connection) throws SQLException {
        PreparedStatement statementForSelectAuthorID = connection.prepareStatement(SELECT_ID_FROM_TYPES);
        statementForSelectAuthorID.setString(1, type_name);

        ResultSet set = statementForSelectAuthorID.executeQuery();
        if (set.next()) {
            return set.getInt(1);
        } else {
            throw new SQLException("Тип продукции " + type_name + " не найден.");
        }
    }
}
