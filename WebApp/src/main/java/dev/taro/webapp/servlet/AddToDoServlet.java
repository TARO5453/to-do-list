package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.ToDo;
import dev.taro.webapp.database.DatabaseManager;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddToDoServlet extends HttpServlet implements Routable {
    // Servlet
    private SecurityService securityService;
    private final DatabaseManager databaseManager = new DatabaseManager();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String title = request.getParameter("title");
        if (title != null && !title.isBlank()) {
            if (securityService.isAuthorized(request)) {
                ToDo td = new ToDo();
                td.setTitle(title);
                td.setUsername(username);
                td.setDone(false);
                databaseManager.create(td);
                response.sendRedirect("/todolist");
            }
            else {
                response.sendRedirect("/login");
            }
        }
        // If the input is blank, print out the error message
        else {
            String error = "Invalid title name.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/add.jsp");
            rd.include(request, response);
        }
    }

    // Routable
    @Override
    public String getMapping() {
        return "/add";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
