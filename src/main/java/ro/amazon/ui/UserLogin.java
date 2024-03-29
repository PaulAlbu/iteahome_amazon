package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.utils.Scan;

import java.security.InvalidParameterException;

public class UserLogin {

    public boolean login() {
        boolean isLoginSuccessful = false;
        int loginAttempts = 0;

        while (!isLoginSuccessful && loginAttempts < 3) {
            try {
                System.out.println("Username: ");
                String username = Scan.scanner.nextLine();

                System.out.println("Password: ");
                String password = Scan.scanner.nextLine();

                UserController.getInstance().login(username, password);
                isLoginSuccessful = true;
                System.out.println("Login successful");
            } catch (InvalidCredentialsException e) {
                System.out.println("Wrong user or password");
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
