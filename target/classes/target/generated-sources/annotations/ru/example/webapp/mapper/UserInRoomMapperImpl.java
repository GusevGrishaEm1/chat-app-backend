package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-19T15:34:10+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class UserInRoomMapperImpl implements UserInRoomMapper {

    @Override
    public UserInRoomDto toDto(UserInRoom userInRoom) {
        if ( userInRoom == null ) {
            return null;
        }

        UserInRoomDto userInRoomDto = new UserInRoomDto();

        userInRoomDto.setUserId( userInRoomUserId( userInRoom ) );
        userInRoomDto.setRoomId( userInRoomRoomId( userInRoom ) );
        userInRoomDto.setId( userInRoom.getId() );
        userInRoomDto.setOwner( userInRoom.isOwner() );
        userInRoomDto.setDisconnected( userInRoom.isDisconnected() );

        return userInRoomDto;
    }

    @Override
    public List<UserInRoomDto> toDto(List<UserInRoom> userInRoomList) {
        if ( userInRoomList == null ) {
            return null;
        }

        List<UserInRoomDto> list = new ArrayList<UserInRoomDto>( userInRoomList.size() );
        for ( UserInRoom userInRoom : userInRoomList ) {
            list.add( toDto( userInRoom ) );
        }

        return list;
    }

    @Override
    public UserInRoom toEntity(UserInRoomDto userInRoom) {
        if ( userInRoom == null ) {
            return null;
        }

        UserInRoom userInRoom1 = new UserInRoom();

        userInRoom1.setId( userInRoom.getId() );
        userInRoom1.setOwner( userInRoom.isOwner() );
        userInRoom1.setDisconnected( userInRoom.isDisconnected() );

        return userInRoom1;
    }

    @Override
    public UserInRoom toEntity(UserInRoomDtoRequest userInRoom) {
        if ( userInRoom == null ) {
            return null;
        }

        UserInRoom userInRoom1 = new UserInRoom();

        userInRoom1.setOwner( userInRoom.isOwner() );
        userInRoom1.setDisconnected( userInRoom.isDisconnected() );

        return userInRoom1;
    }

    private long userInRoomUserId(UserInRoom userInRoom) {
        if ( userInRoom == null ) {
            return 0L;
        }
        User user = userInRoom.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private long userInRoomRoomId(UserInRoom userInRoom) {
        if ( userInRoom == null ) {
            return 0L;
        }
        Room room = userInRoom.getRoom();
        if ( room == null ) {
            return 0L;
        }
        long id = room.getId();
        return id;
    }
}
