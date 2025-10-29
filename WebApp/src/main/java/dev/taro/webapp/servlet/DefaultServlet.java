package dev.taro.webapp.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// This servlet is for redirecting " / " root to other pages

public class DefaultServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect root URL to login if not authenticated. Else redirect to todolist
        boolean authenticated = securityService.isAuthorized(request);
        if (authenticated) response.sendRedirect("/todolist");
        else response.sendRedirect("/login");

    }
}
