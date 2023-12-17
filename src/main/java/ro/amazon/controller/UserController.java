package ro.amazon.controller;

import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.service.UserService;

import java.security.InvalidParameterException;

public class UserController {
    UserService userService = new UserService();

    public void login(String username, String password) throws InvalidCredentialsException {
        validateCredentials(username, password);
        userService.login(username, password);
    }

    private void validateCredentials(String username, String password) {
        if (username.length() < 5) {
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
