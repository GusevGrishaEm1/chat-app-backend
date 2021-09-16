package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.dto.RoomDto;
import ru.example.webapp.domain.dto.RoomDtoRequest;
import java.util.List;

@Mapper
public interface RoomMapper {

    RoomDto toDto(Room room);

    List<RoomDto> toDto(List<Room> rooms);

    Room toEntity(RoomDto room);

    Room toEntity(RoomDtoRequest room);
}
