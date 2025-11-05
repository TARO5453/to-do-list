package dev.taro.webapp.servlet;
import java.io.IOException;

import dev.taro.webapp.database.UserDatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class RegisterServlet extends BaseServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDatabaseManager  userDatabaseManager = UserDatabaseManager.getInstance();
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (userDatabaseManager.createNewAccount(username, password)) {
                response.sendRedirect("/login");
            } else {
                String error = "Username already exists.";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
                rd.include(request, response);
            }
        } else {
            String error = "Username is blank.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
            rd.include(request, response);
        }
    }
}

