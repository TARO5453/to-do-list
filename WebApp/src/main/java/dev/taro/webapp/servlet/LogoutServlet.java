package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet implements Routable {
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // logout and redirect to the login page
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }

    @Override
    public String getMapping() {
        return "/logout";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}