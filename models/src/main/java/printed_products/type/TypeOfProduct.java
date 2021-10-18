package printed_products.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeOfProduct {
    BOOK("Книга"),
    MAGAZINE("Журнал"),
    NEWSPAPER("Газета");

    private String name;

}
