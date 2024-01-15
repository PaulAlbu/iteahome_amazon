package ro.amazon.service;

import ro.amazon.ApplicationContext;
import ro.amazon.dao.UserDAO;
import ro.amazon.entity.User;
import ro.amazon.exceptions.InvalidCredentialsException;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public void login(String username, String password) throws InvalidCredentialsException {
        for (User user : userDAO.getUserList()) {
            if (username.equalsIgnoreCase(user.getUsername())
                    && password.equals(user.getPassword())) {
                ApplicationContext.setCurrentUser(user);
                return;
            }
        }
        throw new InvalidCredentialsException();
    }

    public void signOut() {
        ApplicationContext.setCurrentUser(null);
        BasketService basketService = new BasketService(); //clear the Users basket when logging out
        basketService.clearBasket();
    }
}
