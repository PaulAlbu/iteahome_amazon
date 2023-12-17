package ro.amazon.service;

import ro.amazon.dao.ProductDAO;
import ro.amazon.entity.Product;
import ro.amazon.exceptions.PriceErrorException;
import ro.amazon.exceptions.ProductDatabaseException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public  ArrayList<Product> validateProductsList() throws PriceErrorException, ProductDatabaseException {

        try {
            ArrayList<Product> productsList = productDAO.createProductsList();
            for (Product product: productsList) {
                if (product.getName() == null || product.getProductDescription() == null ){
                    throw new ProductDatabaseException();
                }
                if (product.getPrice() <= 0){
                    throw new PriceErrorException();
                }
            }
            return productsList;

        } catch (FileNotFoundException e){
            System.out.println("Data base exception error");
            System.out.println(e);
            return null;
        }
    }
}
