package dev.taro.webapp.servlet;

import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected final SecurityService securityService = new SecurityService();
}
