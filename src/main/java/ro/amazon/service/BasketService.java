package ro.amazon.service;

import ro.amazon.controller.BasketController;
import ro.amazon.entity.Product;
import ro.amazon.utils.Scan;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketService {


    private HashMap<Product, Integer> productsAndQuantity = new HashMap<>();

    public void basket(Product product, int quantityRequestedByBuyer) {
        productsAndQuantity.put(product, quantityRequestedByBuyer);
    }

    public HashMap<Product, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }


}
