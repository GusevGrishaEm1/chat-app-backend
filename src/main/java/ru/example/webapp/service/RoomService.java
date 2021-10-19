package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.Type;
import ru.example.webapp.domain.dto.disc.DiscInfoDtoRequest;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.exception.UserNotFoundException;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.repository.RoomRepo;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepo roomRepo;
    private final UserInRoomService userInRoomService;
    private final UserService userService;
    private final DiscInfoService discInfoService;
    private RoomMapper roomMapper;

    @Autowired
    public RoomService(RoomRepo roomRepo, UserInRoomService userInRoomService, UserService userService, DiscInfoService discInfoService) {
        this.roomRepo = roomRepo;
        this.userInRoomService = userInRoomService;
        this.userService = userService;
        this.discInfoService = discInfoService;
    }

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
        Room roomEntity = roomMapper.INSTANCE.toEntity(roomDto);
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

    public RoomDto createRoom(String roomName, long userInRoomId) throws
            UserAccessException,
            UserInRoomNotFoundException,
            UserNotFoundException
    {
        UserInRoomDto userInRoom = userInRoomService.getUserInRoom(userInRoomId);
        UserDto user = userService.getUser(userInRoom.getUserId());
        if (!user.isBanned() && !userInRoom.isDisconnected()) {
            RoomDtoRequest newRoom = new RoomDtoRequest();
            newRoom.setName(roomName);
            newRoom.setType(Type.USER_COMMON);
            newRoom.setPrivateRoom(false);
            RoomDto room = addRoom(newRoom);
            UserInRoomDtoRequest newUserInRoom = new UserInRoomDtoRequest();
            newUserInRoom.setRoomId(room.getId());
            newUserInRoom.setUserId(user.getId());
            newUserInRoom.setOwner(true);
            newUserInRoom.setDisconnected(false);
            userInRoomService.addUserInRoom(newUserInRoom);
            return room;
        } else {
            throw new UserAccessException("User " + user.getUsername() + "  does not have permission to do this");
        }
    }

    public RoomDto createPrivateRoom(long firstUserId, long secondUserId) throws
            UserAccessException,
            UserNotFoundException
    {
        UserDto firstUser = userService.getUser(firstUserId);
        UserDto secondUser = userService.getUser(secondUserId);
        if (!firstUser.isBanned()) {
            RoomDtoRequest newPrivateRoom = new RoomDtoRequest();
            newPrivateRoom.setPrivateRoom(true);
            newPrivateRoom.setType(Type.PERSONAL);
            newPrivateRoom.setName(firstUser.getUsername() +"-" + secondUser.getUsername());
            RoomDto privateRoom = addRoom(newPrivateRoom);
            UserInRoomDto firstUserInRoom = new UserInRoomDto();
            firstUserInRoom.setUserId(firstUser.getId());
            firstUserInRoom.setOwner(false);
            firstUserInRoom.setDisconnected(false);
            firstUserInRoom.setRoomId(privateRoom.getId());
            UserInRoomDto secondUserInRoom = new UserInRoomDto();
            secondUserInRoom.setUserId(secondUser.getId());
            secondUserInRoom.setOwner(false);
            secondUserInRoom.setDisconnected(false);
            secondUserInRoom.setRoomId(privateRoom.getId());
            return privateRoom;
        } else {
            throw new UserAccessException("User " + firstUser.getUsername() + " does not have permission to do this");
        }
    }

    public RoomDto renameRoom(long userInRoomId, String oldRoomName, String newRoomName) throws
            UserAccessException,
            UserInRoomNotFoundException,
            UserNotFoundException
    {
        UserInRoomDto userInRoom = userInRoomService.getUserInRoom(userInRoomId);
        UserDto user = userService.getUser(userInRoom.getUserId());
        if ((userInRoom.isOwner() && !user.isBanned()) || (user.getRole() == Role.ADMIN)) {
            RoomDto room = getByName(oldRoomName);
            room.setName(newRoomName);
            return editRoom(room);
        }
        throw new UserAccessException("User " + user.getUsername() + " does not have permission to do this");
    }

    public UserInRoomDto disconnectUser(long whoDisconnect, String username, String roomName, int minutes) throws
            UserAccessException,
            UserInRoomNotFoundException,
            UserNotFoundException
    {
        UserInRoomDto firstUserInRoom = userInRoomService.getUserInRoom(whoDisconnect);
        UserDto firstUser = userService.getUser(firstUserInRoom.getUserId());
        if ((!firstUser.isBanned() && firstUserInRoom.isOwner()) || (firstUser.getRole() == Role.ADMIN)) {
            RoomDto room = getByName(roomName);
            UserDto secondUser = userService.getByUsername(username);
            UserInRoomDto secondUserInRoom = userInRoomService.getByUserAndRoom(secondUser, room);
            secondUserInRoom.setDisconnected(true);
            userInRoomService.editUserInRoom(secondUserInRoom);
            DiscInfoDtoRequest discInfo = new DiscInfoDtoRequest();
            discInfo.setMinutes(minutes);
            discInfo.setUserInRoomId(secondUserInRoom.getId());
            discInfoService.addDiscInfo(discInfo);
            return secondUserInRoom;
        } else {
            throw new UserAccessException("User " + firstUser.getUsername() + " does not have permission to do this");
        }
    }

    public UserInRoomDto connectUser(long whoInvite,
                                     String username,
                                     String roomName) throws
            UserAccessException,
            UserInRoomNotFoundException,
            UserNotFoundException {
        UserInRoomDto firstUserInRoom = userInRoomService.getUserInRoom(whoInvite);
        UserDto firstUser = userService.getUser(firstUserInRoom.getUserId());
        if (!firstUser.isBanned() && (!firstUserInRoom.isDisconnected())) {
            RoomDto room = getByName(roomName);
            UserDto secondUser = userService.getByUsername(username);
            UserInRoomDtoRequest secondUserInRoom = new UserInRoomDtoRequest();
            secondUserInRoom.setOwner(false);
            secondUserInRoom.setDisconnected(false);
            secondUserInRoom.setUserId(secondUser.getId());
            secondUserInRoom.setRoomId(room.getId());
            return userInRoomService.addUserInRoom(secondUserInRoom);
        } else {
            throw new UserAccessException("User " + firstUser.getUsername() + " does not have permission to do this");
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

    public RoomDto getByName(String roomName) {
        return roomMapper.INSTANCE.toDto(roomRepo.findByName(roomName));
    }


}
