package dev.taro.webapp.servlet;

import dev.taro.webapp.Routable;
import dev.taro.webapp.ToDo;
import dev.taro.webapp.database.DatabaseManager;
import dev.taro.webapp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class ToggleToDoServlet extends HttpServlet implements Routable {
    // Servlet
    private SecurityService securityService;
    private final DatabaseManager databaseManager = new DatabaseManager();
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
            if (securityService.isAuthorized(request)) {
                ToDo td = databaseManager.read(username, title);
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

    // Routable
    @Override
    public String getMapping() {
        return "/toggle";
    }
    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
