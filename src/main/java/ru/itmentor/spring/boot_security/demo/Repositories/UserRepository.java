package ru.itmentor.spring.boot_security.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.Model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}