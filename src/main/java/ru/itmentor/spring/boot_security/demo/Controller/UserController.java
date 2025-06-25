package ru.itmentor.spring.boot_security.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.security.CurrentUser;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<User> getUserProfile(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(currentUser.getUser());
    }
}

