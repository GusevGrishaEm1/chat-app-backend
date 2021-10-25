package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T16:17:49+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto toDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setId( room.getId() );
        roomDto.setName( room.getName() );
        roomDto.setType( room.getType() );
        roomDto.setPrivateRoom( room.isPrivateRoom() );

        return roomDto;
    }

    @Override
    public List<RoomDto> toDto(List<Room> rooms) {
        if ( rooms == null ) {
            return null;
        }

        List<RoomDto> list = new ArrayList<RoomDto>( rooms.size() );
        for ( Room room : rooms ) {
            list.add( toDto( room ) );
        }

        return list;
    }

    @Override
    public Room toEntity(RoomDto room) {
        if ( room == null ) {
            return null;
        }

        Room room1 = new Room();

        room1.setId( room.getId() );
        room1.setName( room.getName() );
        room1.setType( room.getType() );
        room1.setPrivateRoom( room.isPrivateRoom() );

        return room1;
    }

    @Override
    public Room toEntity(RoomDtoRequest room) {
        if ( room == null ) {
            return null;
        }

        Room room1 = new Room();

        room1.setName( room.getName() );
        room1.setType( room.getType() );
        room1.setPrivateRoom( room.isPrivateRoom() );

        return room1;
    }
}
