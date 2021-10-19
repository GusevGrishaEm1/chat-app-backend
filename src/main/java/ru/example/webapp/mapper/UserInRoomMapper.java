package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;
import java.util.List;

@Mapper
public interface UserInRoomMapper {

    UserInRoomMapper INSTANCE = Mappers.getMapper( UserInRoomMapper.class );

    @Mapping(source = "user.id" , target = "userId" )
    @Mapping(source = "room.id" , target = "roomId" )
    UserInRoomDto toDto(UserInRoom userInRoom);

    List<UserInRoomDto> toDto(List<UserInRoom> userInRoomList);

    UserInRoom toEntity(UserInRoomDto userInRoom);

    UserInRoom toEntity(UserInRoomDtoRequest userInRoom);
}
