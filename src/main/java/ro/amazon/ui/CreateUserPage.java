package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.utils.Scan;

import java.io.IOException;
import java.security.InvalidParameterException;

public class CreateUserPage {

    private static CreateUserPage createUserPage;

    private CreateUserPage() {
    }

    public static CreateUserPage getCreateUserPageInstance() {
        if (createUserPage == null) {
            createUserPage = new CreateUserPage();
        }
        return createUserPage;
    }

    public void createNewUser() {
        boolean isUserCreatedSuccessfully = false;

        System.out.println("Let's get started...");

        while (!isUserCreatedSuccessfully) {
            try {

                System.out.println("Username should be at least 5 characters long \n" +
                        "can not contain symbol \";\"");
                System.out.println("Username: ");
                String username = Scan.scanner.next();

                System.out.println("Password should be at least 5 characters long \n" +
                        "contain at least 1 uppercase and 1 lowercase letter \n" +
                        "contain at least 1 digit and 1 symbol");
                System.out.println("Password: ");
                String password = Scan.scanner.next();

                System.out.println("Please enter your email address");
                String mail = Scan.scanner.next();

                UserController.getInstance().createNewUser(username, password, mail);
                isUserCreatedSuccessfully = true;
                System.out.println("Account created successfully");

            } catch (InvalidCredentialsException e) {
                System.out.println("Username already exists. Try again with a different username");
            } catch (InvalidParameterException e) {
                System.out.println("Invalid credentials format. " +
                        "Please use valid format for username password and email");
            } catch (IOException e) {
                System.out.println("Database is not available right now, please try again later");
            }
        }
    }
}
