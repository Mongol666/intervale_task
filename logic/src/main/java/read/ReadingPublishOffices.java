package read;

import cashing.publish_offices.CashPublishOffices;
import publishing_office.PublishOffice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReadingPublishOffices {
    /*
      Так как кэширование и чтение данных это одно и то же
      Нет смысла писать новую логику
      И поэтому я взял кэширование всех сущностей и добавил их в этот класс
     */

    /**
     * Метод, который вытаскивает значения из таблицы издательств
     * @return {@link List} издательств
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */
    public static List<PublishOffice> readingPublishOffices() throws SQLException, ClassNotFoundException, IOException {
        return CashPublishOffices.getPublishOffices();
    }
}
