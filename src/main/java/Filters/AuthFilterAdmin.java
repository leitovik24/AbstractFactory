package Filters;

import User.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        String role = (String) session.getAttribute("role");

        if((path.contains("admin")||path.contains("user"))&&role==null){

            request.setAttribute("access", "false");

        }

        else if(path.contains("admin")&&role.contains("admin")){

            request.setAttribute("access", "true" );
        }
        else if(path.contains("user")&&(role.contains("user")&&role.contains("admin"))){

            request.setAttribute("access", "true");
        }

        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
