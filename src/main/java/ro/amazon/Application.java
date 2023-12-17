package ro.amazon;

import ro.amazon.ui.ProductsList;
import ro.amazon.ui.UserLogin;

public class Application {
    public static void main(String[] args) {
        UserLogin login = new UserLogin();
        login.login();


        ProductsList productsList = new ProductsList();
        productsList.displayProductsList();

        //AddToCart
    }

}