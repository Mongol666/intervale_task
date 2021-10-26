package servlets;

import read.ReadingPrintedProducts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/read_products")
public class ReadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("products", ReadingPrintedProducts.readingPrintedProducts());
            req.getRequestDispatcher("/reading").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e.getMessage());
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
