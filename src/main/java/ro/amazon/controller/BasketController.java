package ro.amazon.controller;

import ro.amazon.entity.Product;
import ro.amazon.service.BasketService;
import ro.amazon.utils.Scan;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketController {
    private static BasketController basketController;
    private BasketController() {
    }
    public static BasketController getBasketController(){
        if (basketController == null) {
            basketController = new BasketController();
        }
        return basketController;
    }

    private BasketService basketService = new BasketService();

    private HashMap<Product, Integer> basket = basketService.getProductsAndQuantity();
    public void checkoutForm() {

        boolean validateCheckoutDetailsAreCorrect = false;

        while (validateCheckoutDetailsAreCorrect == false) {
            System.out.println("Please enter your full name:");
            String fullName = Scan.scanner.nextLine();

            System.out.println("Please enter your email address");
            String mail = Scan.scanner.nextLine();
            while (emailValidator(mail) == false) {
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
            System.out.println("Products selected:");
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
            System.out.printf("Total cost is: %.2f%n", totalCost);

            System.out.printf("Are your details correct? Press y if yes, or n if no.\n" +
                    "If n, we shall ask again for the correct order details.");
            String checkoutDetailsAreCorrect = Scan.scanner.nextLine();
            if (checkoutDetailsAreCorrect.toLowerCase().equals("y")) {
                validateCheckoutDetailsAreCorrect = true;
                System.out.println("Order has been sent");
            }
        }
    }


    public boolean emailValidator(String mail) {
        Pattern mailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
        Matcher mailMatcher = mailPattern.matcher(mail);
        return mailMatcher.matches();
    }
}
