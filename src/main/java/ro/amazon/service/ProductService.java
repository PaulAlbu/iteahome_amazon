package ro.amazon.service;

import ro.amazon.dao.ProductDAO;
import ro.amazon.entity.Product;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public ArrayList<Product> validateProductsList() throws PriceException, ProductDatabaseException {

        ArrayList<Product> productsList = productDAO.createProductsList();

        deleteProductIfQuantityIsZero(productsList);

        if (productsList.size() == 0) {
            throw new ProductDatabaseException();
        }

        for (Product product : productsList) {
            if (product.getName() == null || product.getProductDescription() == null || product.getProductID() == null || productsList.size() == 0) {
                throw new ProductDatabaseException();
            }
            if (product.getPrice() <= 0) {
                throw new PriceException();
            }

        }

        return productsList;
    }

    private void deleteProductIfQuantityIsZero(ArrayList<Product> productsList) {
        for (int i = productsList.size() - 1; i >= 0; i--) {
            if (productsList.get(i).getQuantity() <= 0) {
                productsList.remove(i);
            }
        }
    }


    public void checkout(int productID, int quantityRequestedByBuyer) throws ProductDatabaseException {
        productDAO.decreaseProductQuantity(productID, quantityRequestedByBuyer);


    }

}
