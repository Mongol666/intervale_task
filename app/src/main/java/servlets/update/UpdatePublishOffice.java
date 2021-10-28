package servlets.update;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/update_publish_office_servlet", name = "UpdatePublishOfficeServlet")
public class UpdatePublishOffice extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String old_publish_office_name = req.getParameter("old_publish_office_name");
        String new_publish_office_name = req.getParameter("new_publish_office_name");

        try {
            update.UpdatePublishOffice.updateAuthor(old_publish_office_name, new_publish_office_name);
            req.getRequestDispatcher("/index.html").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("exception", e);
            req.getRequestDispatcher("/exception").forward(req, resp);
        }
    }
}
