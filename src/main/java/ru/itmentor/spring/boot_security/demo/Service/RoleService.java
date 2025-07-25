package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.Model.Role;

import java.util.Set;

public interface RoleService {
    Role findById(Long role);

    Set<Role> findAll();

    Set<Role> findRolesByNames(Set<String> roleNames);
}
