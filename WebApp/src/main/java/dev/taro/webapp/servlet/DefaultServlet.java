package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
// Use to map " / " home to log-in page

public class DefaultServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect root URL to login
        response.sendRedirect("/login");
    }
    @Override
    public String getMapping() {
        return "/index.html";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
