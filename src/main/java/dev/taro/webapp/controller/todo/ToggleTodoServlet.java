package dev.taro.webapp.controller.todo;

import dev.taro.webapp.model.Todo;
import dev.taro.webapp.repository.TodoRepository;
import dev.taro.webapp.controller.common.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class ToggleTodoServlet extends BaseServlet {
    // Servlet
    private final TodoRepository databaseManager = TodoRepository.getInstance();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username from the session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String username = (String) session.getAttribute("username");
        String title = request.getParameter("title");
        if (!StringUtils.isBlank(title)) {
            if (authService.isAuthorized(request)) {
                Todo td = databaseManager.read(username, title);
                if (td != null) {
                    databaseManager.updateDone(username, title, !td.isDone());
                    response.sendRedirect("/todolist");
                }
            }
            else {
                response.sendRedirect("/login");
            }
        }
    }
}
