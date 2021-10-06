package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import java.util.List;

@Mapper
public interface UserInRoomMapper {

    UserInRoomMapper INSTANCE = Mappers.getMapper( UserInRoomMapper.class );

    UserInRoomDto toDto(UserInRoom userInRoom);

    List<UserInRoomDto> toDto(List<UserInRoom> userInRoomList);

    UserInRoom toEntity(UserInRoomDto userInRoom);

    UserInRoom toEntity(UserInRoomDtoRequest userInRoom);
}
