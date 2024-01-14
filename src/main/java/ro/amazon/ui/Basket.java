package ro.amazon.ui;

import ro.amazon.controller.BasketController;


public class Basket {
    // cos empety - no prod selected
    // dispplayBascket
    public void displayBasket(){
        BasketController.getBasketController().checkoutForm();

    }
}
