package ro.amazon.controller;

import ro.amazon.entity.Product;
import ro.amazon.exceptions.ExcessiveSelectedQuantityException;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;
import ro.amazon.exceptions.WrongInputException;
import ro.amazon.service.BasketService;
import ro.amazon.service.ProductService;

import java.util.*;

import static ro.amazon.utils.InputHandler.validateAndReturnIntegerInput;
import static ro.amazon.utils.Logger.debugInfo;
import static ro.amazon.utils.Scan.scanner;

public class ProductController {

    // Singleton pattern to create a single instance of the Product controller to be accessed in the UI
    private static ProductController productController;

    private ProductController() throws PriceException, ProductDatabaseException {
    }

    public static ProductController getProductController() throws PriceException, ProductDatabaseException {
        if (productController == null) {
            productController = new ProductController();
        }
        return productController;
    }

    private ProductService productService = new ProductService();


    private BasketService basketService = new BasketService();

//    public ArrayList<Product> getProductsList() {
//        return productsList;
//    }


    public ArrayList<Product> getProductsList() throws PriceException, ProductDatabaseException {
        ArrayList<Product> productsList = new ArrayList<>();
        return productsList = productService.validateProductsList();
    }

    public void addProductsToBascket(int clientProdSelection) throws ProductDatabaseException, ExcessiveSelectedQuantityException, WrongInputException, PriceException {
        int productsIndex = 1;
        ArrayList<Product> productsList = getProductsList();
        if (clientProdSelection > productsList.size()) {
            throw new WrongInputException();
        }

        int quantityRequestedByBuyer = 0;
        for (Product product : productsList) {
            if (clientProdSelection == productsIndex) {
                System.out.println("Please select quantity:");
                try {
                    quantityRequestedByBuyer = validateAndReturnIntegerInput(scanner);
                    if (quantityRequestedByBuyer <= 0) {
                        throw new WrongInputException();
                    }
                } catch (WrongInputException e) {
                    System.out.println(e.getMessage());
                    debugInfo(e.getMessage(), e.fillInStackTrace());
                    addProductsToBascket(clientProdSelection);
                }

                if (quantityRequestedByBuyer <= product.getQuantity() && quantityRequestedByBuyer > 0) {
                    product.setQuantity(product.getQuantity() - quantityRequestedByBuyer);
                    basketService.addProductsToBasket(product, quantityRequestedByBuyer);
                    productService.checkout(product.getProductID(), quantityRequestedByBuyer);
                } else {
                    throw new ExcessiveSelectedQuantityException();
                }
            }
            productsIndex++;
        }
    }
}
