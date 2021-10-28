package servlets.update;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/update_author_servlet", name = "UpdateAuthorServlet")
public class UpdateAuthor extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String old_author_name = req.getParameter("old_author_name");
        String new_author_name = req.getParameter("new_author_name");

        try {
            update.UpdateAuthor.updateAuthor(old_author_name, new_author_name);
            req.getRequestDispatcher("/index.html").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
