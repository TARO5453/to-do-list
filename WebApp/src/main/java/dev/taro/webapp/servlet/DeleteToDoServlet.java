package dev.taro.webapp.servlet;

import dev.taro.webapp.database.TodoDatabaseManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class DeleteToDoServlet extends BaseServlet{
    // Servlet
    private final TodoDatabaseManager databaseManager = TodoDatabaseManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/delete.jsp");
        rd.forward(request, response);
    }
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
        if (title != null && !title.isBlank()) {
            if (securityService.isAuthorized(request)) {
                databaseManager.delete(username, title);
                response.sendRedirect("/todolist");
            }
            else {
                response.sendRedirect("/login");
            }
        }
        // If the input is invalid, print out the error message
        else {
            String error = "Invalid title name.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/delete.jsp");
            rd.include(request, response);
        }
    }

}
