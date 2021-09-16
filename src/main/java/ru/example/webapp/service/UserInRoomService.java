package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
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

    public long deleteUserInRoom(long id) {
        if (userInRoomRepo.findById(id) == null)
            // add throw Exception
            return -1;
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
    public UserInRoomDto getUserInRoom(long id) {
        UserInRoom userInRoomEntity = userInRoomRepo.findById(id);
        if (userInRoomEntity==null)
            // add throw Exception
            return null;
        else {
            return userInRoomMapper.toDto(userInRoomEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserInRoomDto> getListUserInRoom() {
        List<UserInRoom> userInRoomList = userInRoomRepo.findAll();
        if (userInRoomList == null)
            // add throw Exception
            return null;
        else {
            return userInRoomMapper.toDto(userInRoomList);
        }
    }
}
