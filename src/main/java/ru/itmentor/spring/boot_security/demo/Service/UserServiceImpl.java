package ru.itmentor.spring.boot_security.demo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.Repositories.RoleRepository;
import ru.itmentor.spring.boot_security.demo.Repositories.UserRepository;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String SUPER_USERNAME = "Super";

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.getRoles().size());
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        optional.ifPresent(user -> user.getRoles().size());
        return optional;
    }


    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User userUpdates) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (SUPER_USERNAME.equals(existingUser.getUsername())) {
            throw new SecurityException("Cannot update Super user");
        }

        existingUser.setUsername(userUpdates.getUsername());

        if (!userUpdates.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userUpdates.getPassword()));
        }

        existingUser.setEnabled(userUpdates.isEnabled());
        existingUser.setRoles(userUpdates.getRoles());

        return userRepository.save(existingUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (SUPER_USERNAME.equals(user.getUsername())) {
            throw new SecurityException("Cannot delete Super user");
        }

        userRepository.delete(user);
        return true;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}

