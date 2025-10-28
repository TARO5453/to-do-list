package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
    Handle the 404 not found error
 */
public class Error404Servlet extends HttpServlet implements Routable {
    SecurityService securityService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticated = securityService.isAuthorized(request);
        if (authenticated) response.sendRedirect("/todolist");
        else response.sendRedirect("/login");
    }
    @Override
    public String getMapping() {
        return "/error404";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
