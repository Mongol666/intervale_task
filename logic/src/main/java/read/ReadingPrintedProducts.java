package read;

import cashing.product.CashProducts;
import printed_products.printed_product.PrintedProduct;

import java.sql.SQLException;
import java.util.List;

public class ReadingPrintedProducts {
    /*
      Так как кэширование и чтение данных это одно и то же
      Нет смысла писать новую логику
      И поэтому я взял кэширование всех сущностей и добавил их в этот класс
     */

    /**
     * Метод, который вытаскивает значения из таблицы печатной продукции
     * @return {@link List} печатной продукции
     * @throws SQLException: обрабатывается в модуле app
     * @throws ClassNotFoundException: обрабатывается в модуле app
     */

    public static List<PrintedProduct> readingPrintedProducts() throws SQLException, ClassNotFoundException {
        return CashProducts.getPrintedProducts();
    }
}
