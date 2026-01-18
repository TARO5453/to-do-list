package dev.taro.webapp.controller.auth;
import java.io.IOException;

import dev.taro.webapp.repository.UserRepository;
import dev.taro.webapp.controller.common.BaseServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class RegisterServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/register.jsp");
        rd.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = UserRepository.getInstance();
        // extract username and password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isBlank(username) && !StringUtils.isBlank(password)) {
            if (userRepository.createNewAccount(username, password)) {
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

