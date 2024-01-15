package ro.amazon.ui;

import ro.amazon.controller.ProductController;
import ro.amazon.exceptions.ExcessiveSelectedQuantityException;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;
import ro.amazon.exceptions.WrongInputException;

import static ro.amazon.utils.InputHandler.validateAndReturnIntegerInput;
import static ro.amazon.utils.Logger.debugInfo;
import static ro.amazon.utils.Scan.scanner;


public class ProductsList {

    public void displayProductsList() {

        try {
            System.out.println("Please find below our list of available products:\n");
            ProductController.getProductController().displayProductsList();
        } catch (PriceException e) {
            System.out.println(e.getMessage());
            debugInfo(e.getMessage(), e.fillInStackTrace());

        } catch (ProductDatabaseException e) {
            System.out.println(e.getMessage());
            debugInfo(e.getMessage(), e.fillInStackTrace());

        }
    }

    public void addProductsToBasket() {
        System.out.println("What products would you like to purchase today?");
        boolean continueAddingProductsToBasket = true;

        while (continueAddingProductsToBasket) {
            System.out.println("Please select the product/ products you desire by selecting them via their numbers");

            int clientProdSelection = 0;
            try {
                clientProdSelection = validateAndReturnIntegerInput(scanner);
            } catch (WrongInputException e) {
                System.out.println(e.getMessage());
                debugInfo(e.getMessage(), e.fillInStackTrace());
                displayProductsList();
                addProductsToBasket();
            }

            try {
                ProductController.getProductController().addProductsToBascket(clientProdSelection);
            } catch (PriceException | ProductDatabaseException | WrongInputException |
                     ExcessiveSelectedQuantityException e) {
                System.out.println(e.getMessage());
                debugInfo(e.getMessage(), e.fillInStackTrace());

            }
            continueAddingProductsToBasket = goToBasket(continueAddingProductsToBasket);
        }

    }

    public boolean goToBasket(boolean continueAddingProductsToBasket) {
        System.out.println("Add more products to basket? Press 0 to go to your basket or 1 to continue shopping.");
        int stopAddingProductsToBasket = 0;
        try {
            stopAddingProductsToBasket = validateAndReturnIntegerInput(scanner);

        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
            debugInfo(e.getMessage(), e.fillInStackTrace());
            goToBasket(continueAddingProductsToBasket);
        }

        if (stopAddingProductsToBasket == 0) {
            continueAddingProductsToBasket = false;
            BasketPage basketPage = new BasketPage();
            basketPage.displayBasket();
            return continueAddingProductsToBasket;

        } else {
            try {
                ProductController.getProductController().displayProductsList();
            } catch (PriceException | ProductDatabaseException e) {
                System.out.println(e.getMessage());
                debugInfo(e.getMessage(), e.fillInStackTrace());
            }
        }

        return continueAddingProductsToBasket;

    }
}
