package ru.itmentor.spring.boot_security.demo.dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.Service.RoleService;

import java.util.Set;

@Component
public class UserDtoConvert {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserDtoConvert(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public User convertToUser(UserUpdateDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(true);

        Set<Role> roles = roleService.findRolesByNames(dto.getRoles());
        user.setRoles(roles);

        return user;
    }
}
