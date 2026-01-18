package dev.taro.webapp.servlet;

import dev.taro.webapp.database.TodoDatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ToDoListServlet extends BaseServlet{
    private final TodoDatabaseManager databaseManager = TodoDatabaseManager.getInstance();

    // Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticated = securityService.isAuthorized(request);
        System.out.println("ToDoListServlet: Session ID = " + request.getSession().getId());
        System.out.println("ToDoListServlet: Username = " + request.getSession().getAttribute("username"));
        if (authenticated) {
            String username = (String) request.getSession().getAttribute("username");
            request.setAttribute("todos", databaseManager.read(username));
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/todolist.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
