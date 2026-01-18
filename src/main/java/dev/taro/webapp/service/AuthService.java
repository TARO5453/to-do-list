package dev.taro.webapp.service;

import dev.taro.webapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
public class AuthService {

    private final UserRepository userRepository = UserRepository.getInstance();

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        // do checking
        return (username != null && userRepository.exists(username));
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String hashedPassword = userRepository.findUserByUsername(username);
        if (hashedPassword == null) {
            return false;
        }
        boolean isMatched = userRepository.checkPassword(password, hashedPassword);
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

