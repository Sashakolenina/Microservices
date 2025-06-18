package ru.itmentor.spring.boot_security.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.itmentor.spring.boot_security.demo.Model.User;

import java.util.stream.Collectors;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList()));
        this.user = user;

    }

    public User getUser() {
        return user;
    }
}