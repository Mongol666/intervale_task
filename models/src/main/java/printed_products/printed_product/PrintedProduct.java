package printed_products.printed_product;

import authors.Author;
import printed_products.type.TypeOfProduct;
import publishing_office.PublishOffice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class PrintedProduct {
    private int id;
    private String name;
    private Author author;
    private TypeOfProduct type;
    private LocalDate dateOfIssue;
    private PublishOffice publishOffice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintedProduct that = (PrintedProduct) o;
        return id == that.id && name.equals(that.name) && author.equals(that.author) && type == that.type && dateOfIssue.equals(that.dateOfIssue) && publishOffice.equals(that.publishOffice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, type, dateOfIssue, publishOffice);
    }

    @Override
    public String toString() {
        return "["
                + type.getName() +
                ", автор: "
                + author.getName() +
                ", название: "
                + name
                + ", издательство: "
                + publishOffice.getName() +
                ", дата выхода: "
                + dateOfIssue.toString()
                + "]";
    }
}
