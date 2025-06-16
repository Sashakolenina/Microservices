package ru.itmentor.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.Model.Role;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.Repositories.RoleRepository;
import ru.itmentor.spring.boot_security.demo.Service.RoleService;
import ru.itmentor.spring.boot_security.demo.Service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
       this.roleService = roleService;
    }


    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id).orElseThrow());
        model.addAttribute("roles", roleService.findAll());
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";

    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "new";
    }
    @PostMapping("/new")
    public String createUser(@ModelAttribute User user,
                             @RequestParam ("roleType") Set<Long> roles) {
        Set<Role> roleIds = roleService.findByIds(roles);
        user.setRoles(roleIds);
        userService.createUser(user);
        return "redirect:/admin";
    }
}