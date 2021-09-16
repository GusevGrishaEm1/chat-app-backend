package ru.example.webapp.mapper;

import ru.example.webapp.domain.User;
import org.mapstruct.Mapper;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.domain.dto.UserDtoRequest;
import java.util.List;

@Mapper
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto user);

    User toEntity(UserDtoRequest user);
}
