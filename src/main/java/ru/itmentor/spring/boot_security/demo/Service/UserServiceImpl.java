package ru.itmentor.spring.boot_security.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.Repositories.RoleRepository;
import ru.itmentor.spring.boot_security.demo.Repositories.UserRepository;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponseDto;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.mapper.UserMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String SUPER_USERNAME = "Super";
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, UserMapper userMapper, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;

        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::from) // Используем наш метод конвертации
                .toList();
    }


    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return userMapper.userResponse(user);
    }


    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserUpdateDto userUpdates) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (SUPER_USERNAME.equals(existingUser.getUsername())) {
            throw new SecurityException("Cannot update Super user");
        }
        if  (userUpdates.getUsername()!=null && !userUpdates.getUsername().isEmpty()) {
            existingUser.setUsername(userUpdates.getUsername());
        }
        if (userUpdates.getPassword() != null && !userUpdates.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userUpdates.getPassword()));
        }
        if (userUpdates.getRoles() != null && !userUpdates.getRoles().isEmpty()) {
           Set<Role> roleSet = userUpdates.getRoles().stream()
                   .map(role -> roleRepository.findByName(String.valueOf(role))
                           .orElseThrow(()->new IllegalArgumentException("Role not Found")))
                   .collect(Collectors.toSet());
           existingUser.setRoles(roleSet);
        }
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

