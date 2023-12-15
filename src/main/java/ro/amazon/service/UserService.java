package ro.amazon.service;

import ro.amazon.dao.UserDAO;
import ro.amazon.entity.User;
import ro.amazon.exceptions.InvalidCredentialsException;

public class UserService {
    UserDAO userDAO = new UserDAO();

    public void login(String username, String password) throws InvalidCredentialsException {
        for (User user : userDAO.getUserList()) {
            if (username.equalsIgnoreCase(user.getUsername())
                    && password.equals(user.getPassword())) {
                return;
            }
        }
        throw new InvalidCredentialsException();
    }
}
