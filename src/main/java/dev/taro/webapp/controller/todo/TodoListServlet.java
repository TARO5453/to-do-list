package dev.taro.webapp.controller.todo;

import dev.taro.webapp.repository.TodoRepository;
import dev.taro.webapp.controller.common.BaseServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TodoListServlet extends BaseServlet {
    private final TodoRepository databaseManager = TodoRepository.getInstance();

    // Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticated = authService.isAuthorized(request);
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
