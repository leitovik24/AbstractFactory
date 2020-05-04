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

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserService userService = UserService.getInstance();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, email);

            userService.addUser(user);
            resp.setStatus(200);
            String path = req.getContextPath() + "/list";
            resp.sendRedirect(path);



    }
}
