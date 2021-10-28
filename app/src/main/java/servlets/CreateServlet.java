package servlets;


import create.CreatingPrintedProduct;
import printed_products.printed_product.PrintedProduct;
import read.ReadingPrintedProducts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet(value = "/create_product_servlet", name = "CreateProductServlet")
public class CreateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String printed_product_name = req.getParameter("name");
        String author_name = req.getParameter("author_name");
        String publish_office_name = req.getParameter("publish_office_name");
        String type_name = req.getParameter("type_name");
        int year_of_issue = Integer.parseInt(req.getParameter("year_of_issue"));
        int month_of_issue = Integer.parseInt(req.getParameter("month_of_issue"));
        int day_of_issue = Integer.parseInt(req.getParameter("day_of_issue"));

        try {
            Optional<PrintedProduct> optionalPrintedProduct = ReadingPrintedProducts
                    .readingPrintedProducts()
                    .stream().filter(printedProduct -> printedProduct.getName().equals(printed_product_name))
                    .findAny();

            if (optionalPrintedProduct.isPresent()) {
                throw new SQLException();
            }

            PrintedProduct createdPrintedProduct = CreatingPrintedProduct
                    .createPrintedProduct(printed_product_name, author_name,
                                          publish_office_name, type_name,
                                          LocalDate.of(year_of_issue, month_of_issue, day_of_issue));

            req.setAttribute("created_printed_product", createdPrintedProduct);
            req.getRequestDispatcher("created").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
