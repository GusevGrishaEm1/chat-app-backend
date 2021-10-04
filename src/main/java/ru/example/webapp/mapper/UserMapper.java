package ru.example.webapp.mapper;

import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.User;
import org.mapstruct.Mapper;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.user.UserDtoRequest;
import java.util.List;

@Mapper
public interface UserMapper {

    public UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto user);

    User toEntity(UserDtoRequest user);
}
