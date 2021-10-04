package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.mapper.UserInRoomMapper;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.UserInRoomRepo;
import java.util.List;

@Service
public class UserInRoomService {

    @Autowired
    private UserInRoomRepo userInRoomRepo;

    private UserInRoomMapper userInRoomMapper;

    private UserMapper userMapper;

    private RoomMapper roomMapper;

    @Transactional
    public UserInRoomDto addUserInRoom(UserInRoomDtoRequest userInRoomDtoRequest) {
        UserInRoom userInRoomEntity = userInRoomMapper.INSTANCE.toEntity(userInRoomDtoRequest);
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
    public UserInRoomDto editUserInRoom(UserInRoomDto userInRoomDto) {
        UserInRoom userInRoomEntity = userInRoomMapper.toEntity(userInRoomDto);
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

    public UserInRoomDto getByUserAndRoom(UserDto user, RoomDto room) {
        User userDB = userMapper.INSTANCE.toEntity(user);
        Room roomDB = roomMapper.INSTANCE.toEntity(room);
        return userInRoomMapper.INSTANCE.toDto(userInRoomRepo.findByUserAndRoom(userDB, roomDB));
    }

}
