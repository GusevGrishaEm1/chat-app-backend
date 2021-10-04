package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;
import java.util.List;

@Mapper
public interface RoomMapper {

    public RoomMapper INSTANCE = Mappers.getMapper( RoomMapper.class );

    RoomDto toDto(Room room);

    List<RoomDto> toDto(List<Room> rooms);

    Room toEntity(RoomDto room);

    Room toEntity(RoomDtoRequest room);
}
