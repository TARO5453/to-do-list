package dev.taro.webapp.controller.auth;

import dev.taro.webapp.controller.common.BaseServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // logout and redirect to the login page
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }
}