package ro.amazon.service;

import ro.amazon.ApplicationContext;
import ro.amazon.dao.BasketDAO;
import ro.amazon.entity.Product;

import java.util.HashMap;

public class BasketService {
    private BasketDAO basketDAO = new BasketDAO();
    public void addProductsToBasket(Product product, int quantityRequestedByBuyer) {
        HashMap<Product, Integer> basket = ApplicationContext.getCurrentUserBasket();
        if (basket.containsKey(product)) {
            Integer newQuantity = quantityRequestedByBuyer + basket.get(product);
            ApplicationContext.getCurrentUserBasket().put(product, newQuantity);
        }else{
            basket.put(product, quantityRequestedByBuyer);
        }
    }

    public void removeProductsQuantityFromBasket(Product product, int quantityToBeRemoved) {
        HashMap<Product, Integer> basket = ApplicationContext.getCurrentUserBasket();
        basket.put(product, quantityToBeRemoved);
        basketDAO.addProductQuantityBackToTheStock(product.getProductID(), quantityToBeRemoved);

    }

    public HashMap<Product, Integer> getBasket() {
        return ApplicationContext.getCurrentUserBasket();
    }

    public void clearBasket() {
        ApplicationContext.getCurrentUserBasket().clear();
    }

}
