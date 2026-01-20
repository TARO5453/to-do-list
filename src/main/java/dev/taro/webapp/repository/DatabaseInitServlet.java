package dev.taro.webapp.repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
/*
    Create database tables when Tomcat starts
 */
@WebServlet(loadOnStartup = 1, urlPatterns = "/init")
public class DatabaseInitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        UserRepository.getInstance().createTable();
        TodoRepository.getInstance().createTable();
        System.out.println("Database tables initialized");
    }
}

