package ro.amazon.ui;

import ro.amazon.controller.BasketController;


public class BasketPage {
    // cos empety - no prod selected
    // dispplayBascket
    public void displayBasket(){
        BasketController.getBasketController().displayBasket();
    }
}
