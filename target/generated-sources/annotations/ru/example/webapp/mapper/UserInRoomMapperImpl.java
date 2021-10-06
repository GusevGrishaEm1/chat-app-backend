package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T12:43:24+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class UserInRoomMapperImpl implements UserInRoomMapper {

    @Override
    public UserInRoomDto toDto(UserInRoom userInRoom) {
        if ( userInRoom == null ) {
            return null;
        }

        UserInRoomDto userInRoomDto = new UserInRoomDto();

        userInRoomDto.setId( userInRoom.getId() );
        userInRoomDto.setOwner( userInRoom.isOwner() );
        userInRoomDto.setDisconnected( userInRoom.isDisconnected() );
        userInRoomDto.setRoom( roomToRoomDto( userInRoom.getRoom() ) );
        userInRoomDto.setUser( userToUserDto( userInRoom.getUser() ) );

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
        userInRoom1.setRoom( roomDtoToRoom( userInRoom.getRoom() ) );
        userInRoom1.setUser( userDtoToUser( userInRoom.getUser() ) );

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
        userInRoom1.setRoom( roomDtoToRoom( userInRoom.getRoom() ) );
        userInRoom1.setUser( userDtoToUser( userInRoom.getUser() ) );

        return userInRoom1;
    }

    protected RoomDto roomToRoomDto(Room room) {
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

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setBanned( user.isBanned() );
        userDto.setRole( user.getRole() );

        return userDto;
    }

    protected Room roomDtoToRoom(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( roomDto.getId() );
        room.setName( roomDto.getName() );
        room.setType( roomDto.getType() );
        room.setPrivateRoom( roomDto.isPrivateRoom() );

        return room;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );
        user.setBanned( userDto.isBanned() );
        user.setRole( userDto.getRole() );

        return user;
    }
}
