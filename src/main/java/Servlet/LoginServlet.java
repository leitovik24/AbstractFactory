package Servlet;
import Service.UserService;
import User.User;
import org.hibernate.Session;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService = UserService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, email);
        if(userService.isUserExist(user)){
            String role = userService.getUserByName(name).getRole();
            httpSession.setAttribute("role", role);
            if(role.equalsIgnoreCase("admin")){
                String path = req.getContextPath() + "/admin/list";
                resp.sendRedirect(path);
            } else if(role.equalsIgnoreCase("user")){
                String path = req.getContextPath() + "/user";
                resp.sendRedirect(path);

            }
        }
        else {
            String path = req.getContextPath() + "/start";
            resp.sendRedirect(path);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
