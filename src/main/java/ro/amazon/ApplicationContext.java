package ro.amazon;

import ro.amazon.entity.Product;
import ro.amazon.entity.User;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private static User currentUser;

    private static final HashMap<Product, Integer> currentUserBasket = new HashMap<>();

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ApplicationContext.currentUser = currentUser;
    }

    public static HashMap<Product, Integer> getCurrentUserBasket() {
        return currentUserBasket;
    }




}
