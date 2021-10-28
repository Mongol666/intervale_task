package read;

import authors.Author;
import cashing.authors.CashAuthors;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReadingAuthors {
    /*
      Так как кэширование и чтение данных это одно и то же
      Нет смысла писать новую логику
      И поэтому я взял кэширование всех сущностей и добавил их в этот класс
     */

    /**
     * Метод, который вытаскивает значения из таблицы авторов
     * @return {@link List} авторов
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static List<Author> readingAuthors() throws SQLException, ClassNotFoundException, IOException {
        return CashAuthors.getAuthorsFromTable();
    }
}
