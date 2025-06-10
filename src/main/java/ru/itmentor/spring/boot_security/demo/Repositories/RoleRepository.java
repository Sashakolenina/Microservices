package ru.itmentor.spring.boot_security.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.Model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}