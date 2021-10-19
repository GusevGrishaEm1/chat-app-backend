package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.mapper.UserInRoomMapper;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.RoomRepo;
import ru.example.webapp.repository.UserInRoomRepo;
import ru.example.webapp.repository.UserRepo;
import java.util.List;

@Service
public class UserInRoomService {

    @Autowired
    private UserInRoomRepo userInRoomRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoomRepo roomRepo;

    private UserInRoomMapper userInRoomMapper;

    private UserMapper userMapper;

    private RoomMapper roomMapper;

    @Transactional
    public UserInRoomDto addUserInRoom(UserInRoomDtoRequest userInRoom) {
        UserInRoom userInRoomEntity = userInRoomMapper.INSTANCE.toEntity(userInRoom);
        User user = userRepo.findById(userInRoom.getUserId());
        Room room = roomRepo.findById(userInRoom.getRoomId());
        userInRoomEntity.setUser(user);
        userInRoomEntity.setRoom(room);
        userInRoomRepo.save(userInRoomEntity);
        return userInRoomMapper.INSTANCE.toDto(userInRoomEntity);
    }

    public long deleteUserInRoom(long id)  throws UserInRoomNotFoundException {
        if (userInRoomRepo.findById(id) == null)
            throw new UserInRoomNotFoundException("UserInRoom with " + id + " not found");
        else {
            userInRoomRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public UserInRoomDto editUserInRoom(UserInRoomDto userInRoom) {
        UserInRoom userInRoomEntity = userInRoomRepo.findById(userInRoom.getId());
        userInRoomEntity.setDisconnected(userInRoom.isDisconnected());
        userInRoomEntity.setOwner(userInRoom.isOwner());
        userInRoomRepo.save(userInRoomEntity);
        return userInRoomMapper.INSTANCE.toDto(userInRoomEntity);
    }

    @Transactional(readOnly = true)
    public UserInRoomDto getUserInRoom(long id) throws UserInRoomNotFoundException {
        UserInRoom userInRoomEntity = userInRoomRepo.findById(id);
        if (userInRoomEntity==null)
            throw new UserInRoomNotFoundException("UserInRoom with " + id + " not found");
        else {
            return userInRoomMapper.INSTANCE.toDto(userInRoomEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserInRoomDto> getListUserInRoom() throws UserInRoomNotFoundException {
        List<UserInRoom> userInRoomList = userInRoomRepo.findAll();
        if (userInRoomList.isEmpty())
            throw new UserInRoomNotFoundException("UserInRoomList is empty");
        else {
            return userInRoomMapper.INSTANCE.toDto(userInRoomList);
        }
    }

    public UserInRoomDto getByUserAndRoom(UserDto userDto, RoomDto roomDto) {
        User user = userMapper.INSTANCE.toEntity(userDto);
        Room room = roomMapper.INSTANCE.toEntity(roomDto);
        return userInRoomMapper.INSTANCE.toDto(userInRoomRepo.findByUserAndRoom(user, room));
    }

}
