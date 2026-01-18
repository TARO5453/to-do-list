package dev.taro.webapp.servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // do login post logic
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            System.out.println("Before auth: " + request.getSession().getId());
            if (securityService.authenticate(username, password, request)) {
                System.out.println("Session ID: " + request.getSession().getId());
                response.sendRedirect(request.getContextPath() +"/todolist");
                System.out.println("Username in session: " + request.getSession().getAttribute("username"));
            } else {
                String error = "Wrong username or password.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
                rd.forward(request, response);
            }
            System.out.println("After auth: " + request.getSession().getId());
        } else {
            String error = "Username or password is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.forward(request, response);
        }

        // check username and password against database
        // if valid then set username attribute to session via securityService
        // else put error message to render error on the login form

    }

}
