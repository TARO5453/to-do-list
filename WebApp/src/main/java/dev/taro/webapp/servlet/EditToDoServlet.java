package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.database.DatabaseManager;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class EditToDoServlet extends HttpServlet implements Routable {
    // Servlet
    private SecurityService securityService;
    private final DatabaseManager databaseManager = new DatabaseManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/edit.jsp");
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
        String oldTitle = request.getParameter("oldTitle");
        String newTitle = request.getParameter("newTitle");
        if (!StringUtils.isBlank(oldTitle) && !StringUtils.isBlank(newTitle)) {
            if (securityService.isAuthorized(request)) {
                databaseManager.updateTitle(username, oldTitle, newTitle);
                response.sendRedirect("/todolist");
            }
            else {
                response.sendRedirect("/login");
            }
        } else {
            String error = "Title is missing.";
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/edit.jsp");
            rd.include(request, response);
        }
    }

    // Routable
    @Override
    public String getMapping() {
        return "/edit";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}

