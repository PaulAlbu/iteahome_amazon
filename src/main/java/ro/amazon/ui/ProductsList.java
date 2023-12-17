package ro.amazon.ui;

import ro.amazon.controller.ProductController;

public class ProductsList {
    private ProductController productController = new ProductController();
        public void displayProductsList() {
            productController.displayProductsList();
        }
}
