package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.database.DatabaseManager;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ToDoListServlet extends HttpServlet implements Routable {
    private SecurityService securityService;
    private final DatabaseManager databaseManager = new DatabaseManager();

    // Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticated = securityService.isAuthorized(request);
        if (authenticated) {
            String username = (String) request.getSession().getAttribute("username");
            request.setAttribute("todos", databaseManager.read(username));
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/todolist.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
    // Routable
    @Override
    public String getMapping() {
        return "/todolist";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
