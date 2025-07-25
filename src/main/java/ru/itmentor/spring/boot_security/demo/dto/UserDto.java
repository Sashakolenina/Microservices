package ru.itmentor.spring.boot_security.demo.dto;

import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Model.User;

import java.util.Set;
import java.util.stream.Collectors;

public record UserDto(
        Long id,
        String username,
        Set<String> roles
) {
    // Статический метод для конвертации User в UserDto
    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                convertRoles(user.getRoles())
        );
    }

    private static Set<String> convertRoles(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
