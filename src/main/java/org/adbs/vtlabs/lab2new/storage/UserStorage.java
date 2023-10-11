package org.adbs.vtlabs.lab2new.storage;

import org.adbs.vtlabs.lab2new.components.ModelMapperProvider;
import org.adbs.vtlabs.lab2new.model.entity.UserEntity;
import org.adbs.vtlabs.lab2new.model.service.User;
import org.adbs.vtlabs.lab2new.repository.UserRepository;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class UserStorage {
    private static volatile UserStorage instance;
    public static synchronized UserStorage getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserStorage();
        }
        return instance;
    }

    private final UserRepository userRepository = UserRepository.getInstance();
    private final ModelMapper modelMapper = ModelMapperProvider.getInstance();

    public Optional<User> getUserById(Long userId) throws SQLException {
        return userRepository
                .getUserById(userId)
                .map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    public Optional<User> findByUsernameAndHash(String username, String hash) throws SQLException {
        return userRepository
                .findByUsernameAndHash(username, hash)
                .map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    public User save(User user) throws SQLException {
        return modelMapper.map(
                userRepository.save(modelMapper.map(user, UserEntity.class)),
                User.class
        );
    }
}
