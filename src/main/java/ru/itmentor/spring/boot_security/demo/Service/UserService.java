package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.Model.User;

import java.util.*;


public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User userUpdates);

    boolean deleteUser(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
