package servlets;

import printed_products.printed_product.PrintedProduct;
import read.ReadingPrintedProducts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/read_products_servlet", name = "ReadProductServlet")
public class ReadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<PrintedProduct> printedProducts = ReadingPrintedProducts.readingPrintedProducts();
            if (!printedProducts.isEmpty()) {
                req.setAttribute("products", ReadingPrintedProducts.readingPrintedProducts());
                req.getRequestDispatcher("/reading").forward(req, resp);
            } else {
                throw new SQLException();
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
