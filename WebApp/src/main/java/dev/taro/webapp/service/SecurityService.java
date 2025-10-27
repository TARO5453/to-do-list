package dev.taro.webapp.service;

import dev.taro.webapp.database.UserDatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
public class SecurityService {

    private final UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        // do checking
        return (username != null && userDatabaseManager.exists(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String hashedPassword = userDatabaseManager.findUserByUsername(username);
        if (hashedPassword == null) {
            return false;
        }
        boolean isMatched = userDatabaseManager.checkPassword(password, hashedPassword);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}

