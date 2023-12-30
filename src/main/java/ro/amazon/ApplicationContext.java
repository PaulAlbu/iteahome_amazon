package ro.amazon;

import ro.amazon.entity.User;

public class ApplicationContext {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(User currentUser) {
        ApplicationContext.currentUser = currentUser;
    }

}
