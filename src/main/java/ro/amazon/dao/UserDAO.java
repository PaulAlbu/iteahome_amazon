package ro.amazon.dao;

import ro.amazon.entity.User;
import ro.amazon.utils.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class UserDAO {
    private List<User> userList = new ArrayList<>();
    private final String DB_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";

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
            Logger.debugInfo("Database error", e.fillInStackTrace());
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void save(User newUser) throws IOException {
        write(newUser.getUsername() + ";"
                + newUser.getPassword() + ";"
                + newUser.getEmail());
        userList.add(newUser);
    }

    private void write(final String s) throws IOException {
        Files.writeString(
                Path.of(DB_PATH, "users.txt"),
                System.lineSeparator() + s,
                CREATE, APPEND
        );
    }

}
