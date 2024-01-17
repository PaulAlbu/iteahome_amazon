package ro.amazon;

import ro.amazon.ui.HomePage;
import ro.amazon.ui.ProductsList;

public class Application {
    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        homePage.showHomePage();

//        //In showHomePage() trebuei facuta legaura intre cele de mai jos
//        ProductsList productsList = new ProductsList();
//        productsList.displayProductsList();
//        productsList.addProductsToBasket();

    }

}