package ro.amazon.controller;

import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.service.UserService;
import ro.amazon.ui.HomePage;

import java.io.IOException;
import java.security.InvalidParameterException;

public class UserController {
    private final UserService userService = new UserService();
    private static UserController userController;

    private UserController() {
    }

    public static UserController getInstance() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public void login(String username, String password) throws InvalidCredentialsException {
        validateCredentials(username, password);
        userService.login(username, password);
    }

    public void signOut() {
        userService.signOut();
        new HomePage().showHomePage();
    }

    public void createNewUser(String username, String password, String mail) throws InvalidCredentialsException, IOException {
        validateCredentials(username, password);
        if (!BasketController.getBasketInstance().emailValidator(mail)) {
            throw new InvalidParameterException("Invalid email");
        }

        userService.createNewUser(username, password, mail);
    }

    private void validateCredentials(String username, String password) {
        if (username.length() < 5 || username.contains(";")) {
            throw new InvalidParameterException("Invalid credentials");
        }

        String[] symbols = password.split("\\w+");
        String[] digits = password.split("\\D+");
        String[] lowerCase = password.split("[a-z]");
        String[] upperCase = password.split("[A-Z]");

        boolean containsSymbols = symbols.length > 1;
        boolean containsDigits = digits.length > 1;
        boolean containsLowerCase = lowerCase.length > 1;
        boolean containsUpperCase = upperCase.length > 1;

        if (!containsDigits || !containsSymbols
                || !containsLowerCase || !containsUpperCase || password.length() < 5) {
            throw new InvalidParameterException("Invalid credentials");
        }

    }
}
