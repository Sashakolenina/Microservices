package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponseDto;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;

import java.util.List;


public interface UserService {
    List<UserDto> getAllUsers();

    UserResponseDto getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, UserUpdateDto userUpdates);

    boolean deleteUser(Long id);

}
