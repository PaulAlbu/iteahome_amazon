package ro.amazon.ui;

import ro.amazon.controller.ProductController;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;

public class ProductsList {
    private ProductController productController = new ProductController();
        public void displayProductsList() {

            try {
                System.out.println("Please find below our list of available products:\n");
                productController.displayProductsList();
            } catch (PriceException e) {
                System.out.println(e.getMessage());

            } catch (ProductDatabaseException e) {
                System.out.println(e.getMessage());
            }
        }

}
