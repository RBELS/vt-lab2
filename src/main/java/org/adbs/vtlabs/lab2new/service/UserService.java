package org.adbs.vtlabs.lab2new.service;

import org.adbs.vtlabs.lab2new.exception.ErrorCode;
import org.adbs.vtlabs.lab2new.exception.ServiceException;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.storage.UserStorage;

import java.sql.SQLException;
import java.util.Objects;

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

    public User registerUser(String username, String password) {
        try {
            return userStorage.save(new User()
                    .setUsername(username)
                    .setHash(authorityService.generateUserHash(username, password))
            );
        } catch (SQLException e) {
            throw new ServiceException(ErrorCode.REGISTER_ERROR);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERROR);
        }
    }

    public User loginUser(String username, String password) {
        try {
            return userStorage.findByUsernameAndHash(username, authorityService.generateUserHash(username, password))
                    .orElseThrow(() -> new ServiceException(ErrorCode.LOGIN_ERROR));
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.LOGIN_ERROR);
        }
    }
}
