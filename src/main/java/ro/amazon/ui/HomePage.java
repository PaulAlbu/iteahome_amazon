package ro.amazon.ui;

public class HomePage {
    private UserLogin userLogin = new UserLogin();
    private ProductsList productsList = new ProductsList();

    public void showHomePage() {
        System.out.println("////////////// Welcome to Amazon //////////////");

        if (userLogin.login()) {
            productsList.displayProductsList();
        }

    }
}
