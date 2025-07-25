package ru.itmentor.spring.boot_security.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.Service.RoleService;
import ru.itmentor.spring.boot_security.demo.Service.UserService;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserDtoConvert;
import ru.itmentor.spring.boot_security.demo.dto.UserResponseDto;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.security.CurrentUser;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserDtoConvert userDtoConvert;


    public AdminController(UserService userService, RoleService roleService, UserDtoConvert userDtoConvert) {
        this.userService = userService;
        this.roleService = roleService;
        this.userDtoConvert = userDtoConvert;
    }

    @PostMapping("/dashboard")
    public ResponseEntity<User> user(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(currentUser.getUser());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> userList() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getAllUsers(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
        Set<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserUpdateDto userUpdateDto) {
        User createdUser = userService.createUser(userDtoConvert.convertToUser(userUpdateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.from(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody UserUpdateDto userUpdates
    ) {
        User updatedUser = userService.updateUser(id, userUpdates);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}