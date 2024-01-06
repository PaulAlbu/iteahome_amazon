package ro.amazon.ui;

import ro.amazon.controller.ProductController;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;

import static ro.amazon.controller.ProductController.getProductController;

public class ProductsList {

        public void displayProductsList() {

            try {
                System.out.println("Please find below our list of available products:\n");
                ProductController.getProductController().displayProductsList();
            } catch (PriceException e) {
                System.out.println(e.getMessage());

            } catch (ProductDatabaseException e) {
                System.out.println(e.getMessage());
            }
        }

}
