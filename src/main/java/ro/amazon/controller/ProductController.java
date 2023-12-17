package ro.amazon.controller;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.PriceErrorException;
import ro.amazon.exceptions.ProductDatabaseException;
import ro.amazon.service.ProductService;

import java.util.ArrayList;

public class ProductController {
    private ProductService productService = new ProductService();

    public void displayProductsList() {
        try {
            ArrayList<Product> productsList = productService.validateProductsList();
            for (Product product: productsList ) {
                System.out.println(product.getName() + "; Specifications:  " + product.getProductDescription() + "; Price: " + product.getPrice() + " EUR");
            }
        } catch (PriceErrorException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        } catch (ProductDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
}
