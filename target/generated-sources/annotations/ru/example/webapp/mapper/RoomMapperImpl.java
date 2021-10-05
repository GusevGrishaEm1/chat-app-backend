package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-05T12:14:42+0400",
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
        roomDto.setListMessage( messageListToMessageDtoList( room.getListMessage() ) );
        roomDto.setListUserInRoom( userInRoomListToUserInRoomDtoList( room.getListUserInRoom() ) );

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
        room1.setListMessage( messageDtoListToMessageList( room.getListMessage() ) );
        room1.setListUserInRoom( userInRoomDtoListToUserInRoomList( room.getListUserInRoom() ) );

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
        room1.setListMessage( messageDtoListToMessageList( room.getListMessage() ) );
        room1.setListUserInRoom( userInRoomDtoListToUserInRoomList( room.getListUserInRoom() ) );

        return room1;
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

    protected MessageDto messageToMessageDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setId( message.getId() );
        messageDto.setValue( message.getValue() );
        messageDto.setDateOfSend( message.getDateOfSend() );
        messageDto.setCommand( message.isCommand() );
        messageDto.setUser( userToUserDto( message.getUser() ) );
        messageDto.setRoom( toDto( message.getRoom() ) );

        return messageDto;
    }

    protected List<MessageDto> messageListToMessageDtoList(List<Message> list) {
        if ( list == null ) {
            return null;
        }

        List<MessageDto> list1 = new ArrayList<MessageDto>( list.size() );
        for ( Message message : list ) {
            list1.add( messageToMessageDto( message ) );
        }

        return list1;
    }

    protected DiscInfoDto discInfoToDiscInfoDto(DiscInfo discInfo) {
        if ( discInfo == null ) {
            return null;
        }

        DiscInfoDto discInfoDto = new DiscInfoDto();

        discInfoDto.setId( discInfo.getId() );
        discInfoDto.setMinutes( discInfo.getMinutes() );
        discInfoDto.setDateOfDisc( discInfo.getDateOfDisc() );
        discInfoDto.setUserInRoom( userInRoomToUserInRoomDto( discInfo.getUserInRoom() ) );

        return discInfoDto;
    }

    protected UserInRoomDto userInRoomToUserInRoomDto(UserInRoom userInRoom) {
        if ( userInRoom == null ) {
            return null;
        }

        UserInRoomDto userInRoomDto = new UserInRoomDto();

        userInRoomDto.setId( userInRoom.getId() );
        userInRoomDto.setOwner( userInRoom.isOwner() );
        userInRoomDto.setDisconnected( userInRoom.isDisconnected() );
        userInRoomDto.setDiscInfo( discInfoToDiscInfoDto( userInRoom.getDiscInfo() ) );
        userInRoomDto.setRoom( toDto( userInRoom.getRoom() ) );
        userInRoomDto.setUser( userToUserDto( userInRoom.getUser() ) );

        return userInRoomDto;
    }

    protected List<UserInRoomDto> userInRoomListToUserInRoomDtoList(List<UserInRoom> list) {
        if ( list == null ) {
            return null;
        }

        List<UserInRoomDto> list1 = new ArrayList<UserInRoomDto>( list.size() );
        for ( UserInRoom userInRoom : list ) {
            list1.add( userInRoomToUserInRoomDto( userInRoom ) );
        }

        return list1;
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

    protected Message messageDtoToMessage(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setId( messageDto.getId() );
        message.setValue( messageDto.getValue() );
        message.setDateOfSend( messageDto.getDateOfSend() );
        message.setCommand( messageDto.isCommand() );
        message.setUser( userDtoToUser( messageDto.getUser() ) );
        message.setRoom( toEntity( messageDto.getRoom() ) );

        return message;
    }

    protected List<Message> messageDtoListToMessageList(List<MessageDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Message> list1 = new ArrayList<Message>( list.size() );
        for ( MessageDto messageDto : list ) {
            list1.add( messageDtoToMessage( messageDto ) );
        }

        return list1;
    }

    protected DiscInfo discInfoDtoToDiscInfo(DiscInfoDto discInfoDto) {
        if ( discInfoDto == null ) {
            return null;
        }

        DiscInfo discInfo = new DiscInfo();

        discInfo.setId( discInfoDto.getId() );
        discInfo.setMinutes( discInfoDto.getMinutes() );
        discInfo.setDateOfDisc( discInfoDto.getDateOfDisc() );
        discInfo.setUserInRoom( userInRoomDtoToUserInRoom( discInfoDto.getUserInRoom() ) );

        return discInfo;
    }

    protected UserInRoom userInRoomDtoToUserInRoom(UserInRoomDto userInRoomDto) {
        if ( userInRoomDto == null ) {
            return null;
        }

        UserInRoom userInRoom = new UserInRoom();

        userInRoom.setId( userInRoomDto.getId() );
        userInRoom.setOwner( userInRoomDto.isOwner() );
        userInRoom.setDisconnected( userInRoomDto.isDisconnected() );
        userInRoom.setDiscInfo( discInfoDtoToDiscInfo( userInRoomDto.getDiscInfo() ) );
        userInRoom.setRoom( toEntity( userInRoomDto.getRoom() ) );
        userInRoom.setUser( userDtoToUser( userInRoomDto.getUser() ) );

        return userInRoom;
    }

    protected List<UserInRoom> userInRoomDtoListToUserInRoomList(List<UserInRoomDto> list) {
        if ( list == null ) {
            return null;
        }

        List<UserInRoom> list1 = new ArrayList<UserInRoom>( list.size() );
        for ( UserInRoomDto userInRoomDto : list ) {
            list1.add( userInRoomDtoToUserInRoom( userInRoomDto ) );
        }

        return list1;
    }
}
