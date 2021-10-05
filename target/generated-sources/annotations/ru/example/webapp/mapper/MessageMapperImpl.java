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
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-05T12:14:42+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setId( message.getId() );
        messageDto.setValue( message.getValue() );
        messageDto.setDateOfSend( message.getDateOfSend() );
        messageDto.setCommand( message.isCommand() );
        messageDto.setUser( userToUserDto( message.getUser() ) );
        messageDto.setRoom( roomToRoomDto( message.getRoom() ) );

        return messageDto;
    }

    @Override
    public List<MessageDto> toDto(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public Message toEntity(MessageDto message) {
        if ( message == null ) {
            return null;
        }

        Message message1 = new Message();

        message1.setId( message.getId() );
        message1.setValue( message.getValue() );
        message1.setDateOfSend( message.getDateOfSend() );
        message1.setCommand( message.isCommand() );
        message1.setUser( userDtoToUser( message.getUser() ) );
        message1.setRoom( roomDtoToRoom( message.getRoom() ) );

        return message1;
    }

    @Override
    public Message toEntity(MessageDtoRequest Room) {
        if ( Room == null ) {
            return null;
        }

        Message message = new Message();

        message.setValue( Room.getValue() );
        message.setDateOfSend( Room.getDateOfSend() );
        message.setCommand( Room.isCommand() );
        message.setUser( userDtoToUser( Room.getUser() ) );
        message.setRoom( roomDtoToRoom( Room.getRoom() ) );

        return message;
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
        userInRoomDto.setRoom( roomToRoomDto( userInRoom.getRoom() ) );
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

    protected RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setId( room.getId() );
        roomDto.setName( room.getName() );
        roomDto.setType( room.getType() );
        roomDto.setPrivateRoom( room.isPrivateRoom() );
        roomDto.setListMessage( toDto( room.getListMessage() ) );
        roomDto.setListUserInRoom( userInRoomListToUserInRoomDtoList( room.getListUserInRoom() ) );

        return roomDto;
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

    protected List<Message> messageDtoListToMessageList(List<MessageDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Message> list1 = new ArrayList<Message>( list.size() );
        for ( MessageDto messageDto : list ) {
            list1.add( toEntity( messageDto ) );
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
        userInRoom.setRoom( roomDtoToRoom( userInRoomDto.getRoom() ) );
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

    protected Room roomDtoToRoom(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( roomDto.getId() );
        room.setName( roomDto.getName() );
        room.setType( roomDto.getType() );
        room.setPrivateRoom( roomDto.isPrivateRoom() );
        room.setListMessage( messageDtoListToMessageList( roomDto.getListMessage() ) );
        room.setListUserInRoom( userInRoomDtoListToUserInRoomList( roomDto.getListUserInRoom() ) );

        return room;
    }
}
