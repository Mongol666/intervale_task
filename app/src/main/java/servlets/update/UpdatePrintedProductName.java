package servlets.update;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/update_printed_product_name_servlet", name = "UpdatePrintedProductNameServlet")
public class UpdatePrintedProductName extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String old_printed_product_name = req.getParameter("old_printed_product_name");
        String new_printed_product_name = req.getParameter("new_printed_product_name");

        try {
            update.UpdatePrintedProduct.updatePrintedProductName(old_printed_product_name, new_printed_product_name);
            req.getRequestDispatcher("/index.html").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
