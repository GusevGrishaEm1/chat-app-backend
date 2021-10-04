package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.BanInfoDto;
import ru.example.webapp.domain.dto.DiscInfoDto;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.user.UserDtoRequest;
import ru.example.webapp.domain.dto.UserInRoomDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-04T22:54:36+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );
        userDto.setBanned( user.isBanned() );
        userDto.setRole( user.getRole() );
        userDto.setBanInfo( banInfoToBanInfoDto( user.getBanInfo() ) );
        userDto.setListMessage( messageListToMessageDtoList( user.getListMessage() ) );
        userDto.setListUserInRoom( userInRoomListToUserInRoomDtoList( user.getListUserInRoom() ) );

        return userDto;
    }

    @Override
    public List<UserDto> toDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserDto user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );
        user1.setUsername( user.getUsername() );
        user1.setPassword( user.getPassword() );
        user1.setBanned( user.isBanned() );
        user1.setRole( user.getRole() );
        user1.setListMessage( messageDtoListToMessageList( user.getListMessage() ) );
        user1.setListUserInRoom( userInRoomDtoListToUserInRoomList( user.getListUserInRoom() ) );
        user1.setBanInfo( banInfoDtoToBanInfo( user.getBanInfo() ) );

        return user1;
    }

    @Override
    public User toEntity(UserDtoRequest user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setUsername( user.getUsername() );
        user1.setPassword( user.getPassword() );
        user1.setBanned( user.isBanned() );
        user1.setRole( user.getRole() );

        return user1;
    }

    protected BanInfoDto banInfoToBanInfoDto(BanInfo banInfo) {
        if ( banInfo == null ) {
            return null;
        }

        BanInfoDto banInfoDto = new BanInfoDto();

        banInfoDto.setId( banInfo.getId() );
        banInfoDto.setMinutes( banInfo.getMinutes() );
        banInfoDto.setDateOfBan( banInfo.getDateOfBan() );
        banInfoDto.setUser( toDto( banInfo.getUser() ) );

        return banInfoDto;
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
        userInRoomDto.setRoom( roomToRoomDto( userInRoom.getRoom() ) );
        userInRoomDto.setUser( toDto( userInRoom.getUser() ) );

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
        roomDto.setListMessage( messageListToMessageDtoList( room.getListMessage() ) );
        roomDto.setListUserInRoom( userInRoomListToUserInRoomDtoList( room.getListUserInRoom() ) );

        return roomDto;
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
        messageDto.setUser( toDto( message.getUser() ) );
        messageDto.setRoom( roomToRoomDto( message.getRoom() ) );

        return messageDto;
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
        userInRoom.setRoom( roomDtoToRoom( userInRoomDto.getRoom() ) );
        userInRoom.setUser( toEntity( userInRoomDto.getUser() ) );

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

    protected Message messageDtoToMessage(MessageDto messageDto) {
        if ( messageDto == null ) {
            return null;
        }

        Message message = new Message();

        message.setId( messageDto.getId() );
        message.setValue( messageDto.getValue() );
        message.setDateOfSend( messageDto.getDateOfSend() );
        message.setCommand( messageDto.isCommand() );
        message.setUser( toEntity( messageDto.getUser() ) );
        message.setRoom( roomDtoToRoom( messageDto.getRoom() ) );

        return message;
    }

    protected BanInfo banInfoDtoToBanInfo(BanInfoDto banInfoDto) {
        if ( banInfoDto == null ) {
            return null;
        }

        BanInfo banInfo = new BanInfo();

        banInfo.setId( banInfoDto.getId() );
        banInfo.setMinutes( banInfoDto.getMinutes() );
        banInfo.setDateOfBan( banInfoDto.getDateOfBan() );
        banInfo.setUser( toEntity( banInfoDto.getUser() ) );

        return banInfo;
    }
}
