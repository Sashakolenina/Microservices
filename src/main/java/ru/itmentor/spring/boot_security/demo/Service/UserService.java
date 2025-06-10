package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.Model.User;

import java.util.*;


public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User findByUsername(String username);

    boolean createUser(User user, Set<String> roleNames);

    User updateUser(Long id, User userUpdates);

    boolean deleteUser(Long id);

    void initRolesAndAdmin();
}
