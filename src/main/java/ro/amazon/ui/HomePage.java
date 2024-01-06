package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.utils.Scan;

import java.util.Scanner;

public class HomePage {
    private UserLogin userLogin = new UserLogin();
    private ProductsList productsList = new ProductsList();

    public void showHomePage() {
        System.out.println("////////////// Welcome to Amazon //////////////");

        if (userLogin.login()) {

            while (true) {
                System.out.println(" If you want to leave press number '0' \n 0. Sign out Button");

                productsList.displayProductsList();

                String actionNumber = Scan.scanner.next();
                if (actionNumber.equals("0")) {
                    UserController.getInstance().signOut();
                }
            }
        }

    }
}
