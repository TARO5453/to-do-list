package dev.taro.webapp.controller.common;

import dev.taro.webapp.service.AuthService;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected final AuthService authService = new AuthService();
}
