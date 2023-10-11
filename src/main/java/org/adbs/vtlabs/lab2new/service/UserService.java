package org.adbs.vtlabs.lab2new.service;

import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.storage.UserStorage;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class UserService {
    private static UserService instance;
    public static synchronized UserService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserService();
        }
        return instance;
    }

    private final UserStorage userStorage = UserStorage.getInstance();
    private final AuthorityService authorityService = AuthorityService.getInstance();

    public User registerUser(String username, String password) throws SQLException {
        return userStorage.save(new User()
                .setUsername(username)
                .setHash(authorityService.generateUserHash(username, password))
        );
    }

    public User loginUser(String username, String password) throws SQLException {
        Optional<User> user = userStorage.findByUsernameAndHash(username, authorityService.generateUserHash(username, password));
        return user.orElseThrow(() -> new RuntimeException("Failed login attempt."));
    }
}
