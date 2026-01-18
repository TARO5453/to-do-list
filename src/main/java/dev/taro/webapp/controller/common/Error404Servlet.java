package dev.taro.webapp.controller.common;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
    Handle the 404 not found error
 */
public class Error404Servlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticated = authService.isAuthorized(request);
        if (authenticated) response.sendRedirect("/todolist");
        else response.sendRedirect("/login");
    }
}
