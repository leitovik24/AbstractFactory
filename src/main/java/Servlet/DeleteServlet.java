package Servlet;

import Service.UserService;
import User.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("access") == "true") {
            long id = Long.parseLong(req.getParameter("id"));
            userService.deleteUser(id);
            String path = req.getContextPath() + "/admin/list";
            resp.sendRedirect(path);
        }
        else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
