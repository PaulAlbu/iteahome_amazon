package ro.amazon.controller;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.ExcessiveSelectedQuantityException;
import ro.amazon.exceptions.WrongInputException;
import ro.amazon.service.BasketService;
import ro.amazon.utils.InputHandler;
import ro.amazon.utils.Logger;
import ro.amazon.utils.Scan;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ro.amazon.utils.Logger.debugInfo;


public class BasketController {
    private static BasketController basketController;
    private BasketService basketService = new BasketService();


    private BasketController() {
    }

    public static BasketController getBasketInstance() {
        if (basketController == null) {
            basketController = new BasketController();
        }
        return basketController;
    }


    public void displayBasket() {
        HashMap<Product, Integer> basket = basketService.getBasket();
        if (basket.isEmpty()){
            System.out.println("You have no products in your basket.");
        } else {
            System.out.println("Products in basket:");
            double totalCost = 0;
            int productNr = 1;
            for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
                Product product = entry.getKey();
                Integer productQuantity = entry.getValue();
                double productPrice = product.getPrice() * productQuantity;
                totalCost += productPrice;
                System.out.printf("%d. Product: %s - Quantity: %d; Cost: %.2f EUR%n", productNr, product.getName(), productQuantity, productPrice);
                productNr++;
            }
            System.out.printf("---%nTotal cost is: %.2f EUR%n", totalCost);
            editBasketOrGoToCheckout(basket);
        }
    }

    public void editBasketOrGoToCheckout(HashMap<Product, Integer> basket) {
        System.out.println("Would you like to go to checkout (press 0) / edit the basket (press 1) / sign-out (press -1)");
        try {
            int chekoutOrEditBasket = InputHandler.validateAndReturnIntegerInput(Scan.scanner);

            if (chekoutOrEditBasket == -1) {
                UserController.getInstance().signOut();
            } else if (chekoutOrEditBasket == 0) {
                checkoutForm();
            } else {
                boolean editBasket = true;
                while (editBasket) {
                    System.out.println("Please select product number from the list which quantity you would like to be decreased: ");
                    int prodNr = InputHandler.validateAndReturnIntegerInput(Scan.scanner);
                    if (prodNr > basket.size()) {
                        try {
                            throw new WrongInputException();
                        } catch (WrongInputException e){
                            System.out.println(e.getMessage());
                            debugInfo(e.getMessage(), e.fillInStackTrace());
                            System.out.println("Please select product number from the list which quantity you would like to be decreased: ");
                            prodNr = InputHandler.validateAndReturnIntegerInput(Scan.scanner);
                        }
                    }

                    System.out.println("Please enter new quantity: ");
                    int quantityToBeDecreased = InputHandler.validateAndReturnIntegerInput(Scan.scanner);

                    int i = 1;
                    for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
                        Product product = entry.getKey();
                        Integer productQuantity = entry.getValue();
                        if (i == prodNr) {
                            if (quantityToBeDecreased > productQuantity  || quantityToBeDecreased < 0) {
                                try {
                                    throw new ExcessiveSelectedQuantityException();
                                } catch (ExcessiveSelectedQuantityException e) {
                                    System.out.println(e.getMessage());
                                    debugInfo(e.getMessage(), e.fillInStackTrace());
                                }
                            } else {
                                basketService.removeProductsQuantityFromBasket(product, quantityToBeDecreased);
                                displayFinalBasket();
                            }
                        }
                        i++;
                    }
                    System.out.println("Continue editing basket? 0 to go to checkout, any other nr to continue editing");
                    int continueEditingBasket = InputHandler.validateAndReturnIntegerInput(Scan.scanner);
                    if (continueEditingBasket == 0) {
                        editBasket = false;

                    }
                }
                checkoutForm();

            }


        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
            Logger.debugInfo(e.getMessage(), e.fillInStackTrace());
        }
    }

    public void checkoutForm() {
        boolean validateCheckoutDetailsAreCorrect = false;

        while (!validateCheckoutDetailsAreCorrect) {
            System.out.println("Please enter your full name:");
            String fullName = Scan.scanner.nextLine();

            System.out.println("Please enter your email address");
            String mail = Scan.scanner.nextLine();
            while (!emailValidator(mail)) {
                System.out.println("Please insert a valid email address");
                mail = Scan.scanner.nextLine();
            }

            System.out.println("Please enter the delivery address");
            String address = Scan.scanner.nextLine();

            System.out.println("Please enter your phone number");
            String phoneNr = Scan.scanner.nextLine();

            System.out.printf("Your delivery details are:%n" +
                    "Name: %s%n" +
                    "Mail: %s%n" +
                    "Address: %s%n" +
                    "Phone Nr: %s%n", fullName, mail, address, phoneNr);
            displayFinalBasket();
            System.out.printf("Are your details correct? Press y to confirm, or n to edit, or -1 to sign-out: ");
            String checkoutDetailsAreCorrect = Scan.scanner.nextLine();
            if (checkoutDetailsAreCorrect.toLowerCase().equals("y")) {
                validateCheckoutDetailsAreCorrect = true;
                System.out.println("Order has been sent");
            } else if (checkoutDetailsAreCorrect.equals("-1")) {
                UserController.getInstance().signOut();
            }
        }
    }

    private void displayFinalBasket() {

        HashMap<Product, Integer> basket = basketService.getBasket();
        System.out.println("Products in basket:");
        double totalCost = 0;
        int productNr = 1;
        for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
            Product product = entry.getKey();
            Integer productQuantity = entry.getValue();
            double productPrice = product.getPrice() * productQuantity;
            totalCost += productPrice;
            System.out.printf("%d. Product: %s - Quantity: %d; Cost: %.2f EUR%n", productNr, product.getName(), productQuantity, productPrice);
            productNr++;
        }
        System.out.printf("---%nTotal cost is: %.2f EUR%n", totalCost);
    }


    public boolean emailValidator(String mail) {
        Pattern mailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher mailMatcher = mailPattern.matcher(mail);
        return mailMatcher.matches();
    }
}