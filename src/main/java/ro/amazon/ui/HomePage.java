package ro.amazon.ui;

import ro.amazon.controller.UserController;
import ro.amazon.utils.InputHandler;
import ro.amazon.utils.Scan;

public class HomePage {
    private UserLogin userLogin = new UserLogin();
    private ProductsList productsList = new ProductsList();

    public void showHomePage() {
        System.out.println("////////////// Welcome to Amazon //////////////");

        while (true) {
            System.out.println("Please chose one of the available actions:");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            try {
                int loginOrCreate = InputHandler.validateAndReturnIntegerInput(Scan.scanner);

                if (loginOrCreate == 1) {
                    login();
                } else if (loginOrCreate == 2) {
                    CreateUserPage.getCreateUserPageInstance().createNewUser();
                } else {
                    System.out.println("The action " + loginOrCreate + " does not exist. Please chose one of the available actions");
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
            }

        }
    }

    private void login() {
        if (userLogin.login()) {

            while (true) {
                System.out.println(" - If you want to leave type -1 \n - To buy a product type 1");

                int productsCount = productsList.displayProductsList();

                try {

                    int actionNumber = InputHandler.validateAndReturnIntegerInput(Scan.scanner);
                    if (actionNumber == -1) {
                        UserController.getInstance().signOut();
                    } else {
                        productsList.addProductsToBasket(productsCount);
                        System.out.println("\n---");
                    }

                } catch (Exception e) {
                    System.out.println("Wrong input");
                }
            }
        }
    }
}
