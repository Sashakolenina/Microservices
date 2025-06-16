package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.Model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findById(Long role);

    List<Role> findAll();

    Set<Role> findByIds(Set<Long> roles);
}
