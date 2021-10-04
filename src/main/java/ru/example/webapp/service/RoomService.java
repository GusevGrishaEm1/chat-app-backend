package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.repository.RoomRepo;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserInRoomService userInRoomService;

    @Autowired
    private DiscInfoService discInfoService;

    private RoomMapper roomMapper;

    @Transactional
    public RoomDto addRoom(RoomDtoRequest roomDtoRequest) {
        Room roomEntity = roomMapper.INSTANCE.toEntity(roomDtoRequest);
        roomRepo.save(roomEntity);
        return roomMapper.INSTANCE.toDto(roomEntity);
    }

    public long deleteRoom(long id) throws RoomNotFoundException {
        if (roomRepo.findById(id) == null)
            throw new RoomNotFoundException("Room with id " + id + " not found");
        else {
            roomRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public RoomDto editRoom(RoomDto roomDto) {
        Room roomEntity = roomMapper.toEntity(roomDto);
        roomRepo.save(roomEntity);
        return roomMapper.INSTANCE.toDto(roomEntity);
    }

    @Transactional(readOnly = true)
    public RoomDto getRoom(long id) throws RoomNotFoundException {
        Room roomEntity = roomRepo.findById(id);
        if (roomEntity == null)
            throw new RoomNotFoundException("Room with id " + id + " not found");
        else {
            return roomMapper.INSTANCE.toDto(roomEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<RoomDto> getRooms() throws RoomNotFoundException {
        List<Room> rooms = roomRepo.findAll();
        if (rooms.isEmpty())
            throw new RoomNotFoundException("RoomList is empty");
        else {
            return roomMapper.INSTANCE.toDto(rooms);
        }
    }

    public RoomDto createRoom(RoomDtoRequest roomDtoRequest,
                              UserDto userDto,
                              UserInRoomDtoRequest userInRoomDtoRequest) throws UserAccessException
    {
        if (!userDto.isBanned()) {
            RoomDto roomDto = addRoom(roomDtoRequest);
            userInRoomDtoRequest.setRoom(roomDto);
            userInRoomDtoRequest.setUser(userDto);
            userInRoomDtoRequest.setOwner(true);
            userInRoomDtoRequest.setDisconnected(false);
            userInRoomService.addUserInRoom(userInRoomDtoRequest);
            return roomDto;
        } else {
            throw new UserAccessException("User " + userDto.getUsername() + "  does not have permission to do this");
        }
    }

    public RoomDto createPrivateRoom(RoomDtoRequest roomDtoRequest,
                                     UserDto userDtoFirst,
                                     UserInRoomDtoRequest userInRoomDtoRequestFirst,
                                     UserDto userDtoSecond,
                                     UserInRoomDtoRequest userInRoomDtoRequestSecond) throws UserAccessException
    {
        if (!userDtoFirst.isBanned()) {
            RoomDto roomDto = addRoom(roomDtoRequest);
            userInRoomDtoRequestFirst.setRoom(roomDto);
            userInRoomDtoRequestFirst.setUser(userDtoFirst);
            userInRoomDtoRequestFirst.setOwner(true);
            userInRoomDtoRequestFirst.setDisconnected(false);
            userInRoomDtoRequestSecond.setRoom(roomDto);
            userInRoomDtoRequestSecond.setUser(userDtoSecond);
            userInRoomDtoRequestSecond.setOwner(true);
            userInRoomDtoRequestSecond.setDisconnected(false);
            return roomDto;
        } else {
            throw new UserAccessException("User " + userDtoFirst.getUsername() + " does not have permission to do this");
        }
    }

    public RoomDto renameRoom(UserInRoomDto userInRoomDto, String newName) throws UserAccessException {
        if ((userInRoomDto.isOwner() && !userInRoomDto.getUser().isBanned()) || (userInRoomDto.getUser().getRole() == Role.ADMIN)) {
            userInRoomDto.getRoom().setName(newName);
            return editRoom(userInRoomDto.getRoom());
        }
        throw new UserAccessException("User " + userInRoomDto.getUser().getUsername() + " does not have permission to do this");
    }

    public UserInRoomDto disconnectUserFromRoom(UserInRoomDto whoDisconnect,
                                                UserInRoomDto whoIsDisconnected,
                                                DiscInfoDtoRequest discInfoDtoRequest) throws UserAccessException
    {
        if ((!whoDisconnect.getUser().isBanned() && whoDisconnect.isOwner()) || (whoDisconnect.getUser().getRole() == Role.ADMIN)) {
            whoIsDisconnected.setDisconnected(true);
            discInfoService.addDiscInfo(discInfoDtoRequest);
            return whoIsDisconnected;
        } else {
            throw new UserAccessException("User " + whoDisconnect.getUser().getUsername() + " does not have permission to do this");
        }
    }

    public UserInRoomDto inviteUserInRoom(UserInRoomDto whoInvite,
                                          UserInRoomDtoRequest whoIsInvited,
                                          RoomDto roomDto,
                                          UserDto userDto) throws UserAccessException
    {
        if (!whoInvite.getUser().isBanned() && (!whoIsInvited.isDisconnected() && !whoIsInvited.getUser().isBanned() ) ) {
            whoIsInvited.setRoom(roomDto);
            whoIsInvited.setUser(userDto);
            whoIsInvited.setOwner(false);
            whoIsInvited.setDisconnected(false);
            return userInRoomService.addUserInRoom(whoIsInvited);
        } else {
            throw new UserAccessException("User " + whoInvite.getUser().getUsername() + " does not have permission to do this");
        }
    }

    public long deleteRoomByName(String name) throws RoomNotFoundException {
        Room roomFromDB = roomRepo.findByName(name);
        if(roomFromDB!= null) {
            deleteRoom(roomFromDB.getId());
        }
        else {
            throw new RoomNotFoundException("Room with name " + name + " not found");
        }
        return roomFromDB.getId();
    }

    public RoomDto getByName(String username) {
        return roomMapper.toDto(roomRepo.findByName(username));
    }


}
