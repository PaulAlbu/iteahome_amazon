package ro.amazon.controller;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;
import ro.amazon.service.ProductService;

import java.util.ArrayList;

public class ProductController {

    // Singleton pattern to create a single instance of the Product controller to be accessed in the UI
    private static ProductController productController;
    private ProductController() {
    }
    public static ProductController getProductController(){
        if (productController == null){
            productController = new ProductController();
        }
        return productController;
    }


    private ProductService productService = new ProductService();


    public void displayProductsList() throws PriceException, ProductDatabaseException {

        ArrayList<Product> productsList = productService.validateProductsList();
        int productsIndex = 1;
        for (Product product : productsList) {
            System.out.println(productsIndex + ". "
                    + product.getName()
                    + "; Specifications: " + product.getProductDescription()
                    + "; Price: " + product.getPrice() + " EUR"
                    + "; Quantity left: " + product.getQuantity());
            productsIndex++;
        }
    }
}
