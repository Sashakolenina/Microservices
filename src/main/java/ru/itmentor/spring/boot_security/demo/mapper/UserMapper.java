package ru.itmentor.spring.boot_security.demo.mapper;

import org.mapstruct.Mapper;
import ru.itmentor.spring.boot_security.demo.Model.User;
import ru.itmentor.spring.boot_security.demo.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto userResponse(User user);

}
