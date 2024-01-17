package ro.amazon.service;

import ro.amazon.ApplicationContext;
import ro.amazon.controller.ProductController;
import ro.amazon.dao.UserDAO;
import ro.amazon.entity.Product;
import ro.amazon.entity.User;
import ro.amazon.exceptions.InvalidCredentialsException;
import ro.amazon.exceptions.PriceException;
import ro.amazon.exceptions.ProductDatabaseException;
import ro.amazon.ui.ProductsList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static ro.amazon.utils.Logger.debugInfo;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public void login(String username, String password) throws InvalidCredentialsException {
        for (User user : userDAO.getUserList()) {
            if (username.equalsIgnoreCase(user.getUsername())
                    && password.equals(user.getPassword())) {
                ApplicationContext.setCurrentUser(user);
                return;
            }
        }
        throw new InvalidCredentialsException();
    }

    public void signOut() {

        ArrayList<Product> productArrayList = null;
        try {
            productArrayList = ProductController.getProductController().getProductsList();
        } catch (PriceException | ProductDatabaseException e) {
            System.out.println(e.getMessage());
            debugInfo(e.getMessage(), e.fillInStackTrace());
        }



        BasketService basketService = new BasketService(); //clear the Users basket when logging out
        for (Map.Entry<Product, Integer> entry :  basketService.getBasket().entrySet()) {
            Product product = entry.getKey();
            Integer productQuantity = entry.getValue();
            basketService.removeProductsQuantityFromBasket(product, productQuantity);
            for (Product element : productArrayList) {
                if (element.getProductID() == product.getProductID()) {
                    element.setQuantity(element.getQuantity() + productQuantity);
                }
            }
        }


        ApplicationContext.setCurrentUser(null);

        basketService.clearBasket();
    }

    public void createNewUser(String username, String password, String mail) throws InvalidCredentialsException, IOException {

        for (User user : userDAO.getUserList()) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                throw new InvalidCredentialsException();
            }
        }

        User newUser = new User(username, password, mail);
        userDAO.save(newUser);
    }
}
