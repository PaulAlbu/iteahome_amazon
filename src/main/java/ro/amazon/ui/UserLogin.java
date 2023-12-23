package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.utils.Logger;
import ro.amazon.utils.Scan;

import java.security.InvalidParameterException;

public class UserLogin {
    UserController userController = new UserController();

    public boolean login() {
        boolean isLoginSuccessful = false;
        int loginAttempts = 0;

        while (!isLoginSuccessful && loginAttempts < 3) {
            try {
                Logger.userInfo("Username: ");
                String username = Scan.scanner.next();

                Logger.userInfo("Password: ");
                String password = Scan.scanner.next();

                userController.login(username, password);
                isLoginSuccessful = true;
                Logger.userInfo("Login successful");
            } catch (InvalidCredentialsException e) {
                Logger.userInfo("Wrong user or password");
                isLoginSuccessful = false;
                loginAttempts++;
            } catch (InvalidParameterException e) {
                Logger.userInfo("Invalid credentials format");
                isLoginSuccessful = false;
                loginAttempts++;
            }
        }
        return isLoginSuccessful;
    }
}
