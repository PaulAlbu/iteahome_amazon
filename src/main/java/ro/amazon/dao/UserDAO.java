package ro.amazon.dao;

import ro.amazon.entity.User;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UserDAO {
    private final List<User> userList = new ArrayList<>();

    public UserDAO() {

        URI usersFile = URI.create("file://"
                + Objects.requireNonNull(getClass().getClassLoader().getResource("Users.txt")).getPath());

        try (Stream<String> stream = Files.lines(Paths.get(usersFile))) {
            stream.forEach(userString -> {
                String[] userDetails = userString.split(";");
                User user = new User(userDetails[0], userDetails[1], userDetails[2]);
                userList.add(user);
            });

        } catch (IOException e) {
            throw new IllegalStateException("Database error", e);
        }
    }

    public List<User> getUserList() {
        return userList;
    }

}
