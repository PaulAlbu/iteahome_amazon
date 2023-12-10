package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.exceptions.InvalidCredentialsException;

import java.security.InvalidParameterException;
import java.util.Scanner;

public class UserLogin {
    UserController userController = new UserController();

    public boolean login() {
        boolean isLoginSuccessful = false;
        int loginAttempts = 0;

        while (!isLoginSuccessful && loginAttempts < 3) {
            try {
                System.out.println("Username: ");
                Scanner keyboard = new Scanner(System.in);
                String username = keyboard.next();

                System.out.println("Password: ");
                String password = keyboard.next();

                userController.login(username, password);
                isLoginSuccessful = true;
                System.out.println("Login successful");
            } catch (InvalidCredentialsException e) {
                System.out.println("Invalid credentials");
                isLoginSuccessful = false;
                loginAttempts++;
            } catch (InvalidParameterException e) {
                System.out.println("Invalid credentials format");
                isLoginSuccessful = false;
                loginAttempts++;
            }
        }
        return isLoginSuccessful;
    }
}
