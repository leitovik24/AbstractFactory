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

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getAttribute("access") == "true") {

            UserService userService = UserService.getInstance();
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String role = req.getParameter("role");
            User user = new User(name, email, role);

            userService.addUser(user);
            resp.setStatus(200);
            String path = req.getContextPath() + "/admin/list";
            resp.sendRedirect(path);
        }
        else{
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }


    }
}
