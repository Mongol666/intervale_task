package read;

import cashing.types.CashTypes;
import printed_products.type.TypeOfProduct;

import java.sql.SQLException;
import java.util.List;

public class ReadingTypes {
    /*
      Так как кэширование и чтение данных это одно и то же
      Нет смысла писать новую логику
      И поэтому я взял кэширование всех сущностей и добавил их в этот класс
     */

    /**
     * Метод, который вытаскивает значения из таблицы типов печатной продукции
     * @return {@link List} типов печатной продукции
     * @throws SQLException : обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */

    public static List<TypeOfProduct> readingTypes() throws SQLException, ClassNotFoundException {
        return CashTypes.getTypes();
    }
}
