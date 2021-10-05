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
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-05T12:14:43+0400",
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
        userInRoomDto.setDiscInfo( discInfoToDiscInfoDto( userInRoom.getDiscInfo() ) );
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
        userInRoom1.setDiscInfo( discInfoDtoToDiscInfo( userInRoom.getDiscInfo() ) );
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
        userInRoom1.setDiscInfo( userInRoom.getDiscInfo() );
        userInRoom1.setRoom( roomDtoToRoom( userInRoom.getRoom() ) );
        userInRoom1.setUser( userDtoToUser( userInRoom.getUser() ) );

        return userInRoom1;
    }

    protected DiscInfoDto discInfoToDiscInfoDto(DiscInfo discInfo) {
        if ( discInfo == null ) {
            return null;
        }

        DiscInfoDto discInfoDto = new DiscInfoDto();

        discInfoDto.setId( discInfo.getId() );
        discInfoDto.setMinutes( discInfo.getMinutes() );
        discInfoDto.setDateOfDisc( discInfo.getDateOfDisc() );
        discInfoDto.setUserInRoom( toDto( discInfo.getUserInRoom() ) );

        return discInfoDto;
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
        messageDto.setRoom( roomToRoomDto( message.getRoom() ) );

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

    protected RoomDto roomToRoomDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDto roomDto = new RoomDto();

        roomDto.setId( room.getId() );
        roomDto.setName( room.getName() );
        roomDto.setType( room.getType() );
        roomDto.setPrivateRoom( room.isPrivateRoom() );
        roomDto.setListMessage( messageListToMessageDtoList( room.getListMessage() ) );
        roomDto.setListUserInRoom( toDto( room.getListUserInRoom() ) );

        return roomDto;
    }

    protected DiscInfo discInfoDtoToDiscInfo(DiscInfoDto discInfoDto) {
        if ( discInfoDto == null ) {
            return null;
        }

        DiscInfo discInfo = new DiscInfo();

        discInfo.setId( discInfoDto.getId() );
        discInfo.setMinutes( discInfoDto.getMinutes() );
        discInfo.setDateOfDisc( discInfoDto.getDateOfDisc() );
        discInfo.setUserInRoom( toEntity( discInfoDto.getUserInRoom() ) );

        return discInfo;
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
        message.setRoom( roomDtoToRoom( messageDto.getRoom() ) );

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

    protected List<UserInRoom> userInRoomDtoListToUserInRoomList(List<UserInRoomDto> list) {
        if ( list == null ) {
            return null;
        }

        List<UserInRoom> list1 = new ArrayList<UserInRoom>( list.size() );
        for ( UserInRoomDto userInRoomDto : list ) {
            list1.add( toEntity( userInRoomDto ) );
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
