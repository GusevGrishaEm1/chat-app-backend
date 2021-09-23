package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.mapper.UserInRoomMapper;
import ru.example.webapp.repository.UserInRoomRepo;
import java.util.List;

@Service
public class UserInRoomService {

    @Autowired
    UserInRoomRepo userInRoomRepo;

    UserInRoomMapper userInRoomMapper;

    @Transactional
    public UserInRoomDto addUserInRoom(UserInRoomDtoRequest userInRoomDtoRequest) {
        UserInRoom userInRoomEntity = userInRoomMapper.toEntity(userInRoomDtoRequest);
        userInRoomRepo.save(userInRoomEntity);
        return userInRoomMapper.toDto(userInRoomEntity);
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
        return userInRoomMapper.toDto(userInRoomEntity);
    }

    @Transactional(readOnly = true)
    public UserInRoomDto getUserInRoom(long id) throws UserInRoomNotFoundException {
        UserInRoom userInRoomEntity = userInRoomRepo.findById(id);
        if (userInRoomEntity==null)
            throw new UserInRoomNotFoundException("UserInRoom with " + id + " not found");
        else {
            return userInRoomMapper.toDto(userInRoomEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserInRoomDto> getListUserInRoom() throws UserInRoomNotFoundException {
        List<UserInRoom> userInRoomList = userInRoomRepo.findAll();
        if (userInRoomList.isEmpty())
            throw new UserInRoomNotFoundException("UserInRoomList is empty");
        else {
            return userInRoomMapper.toDto(userInRoomList);
        }
    }

}
