package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.domain.dto.UserDtoRequest;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.UserRepo;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    UserMapper userMapper;

    @Transactional
    public UserDto addUser(UserDtoRequest userDtoRequest) {
        User userFromDb = userRepo.findByUsername(userDtoRequest.getUsername());
        if (userFromDb != null) {
            // add throw Exception
            return null;
        }
        else {
            User userEntity = userMapper.toEntity(userDtoRequest);
            userRepo.save(userEntity);
            return userMapper.toDto(userEntity);
        }
    }

    public long deleteUser(long id) {
        if (userRepo.findById(id) == null)
            // add throw Exception
            return -1;
        else {
            userRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public UserDto editUser(UserDto userDto) {
        User userFromDb = userRepo.findByUsername(userDto.getUsername());
        if (userFromDb != null && userFromDb.getId() != userDto.getId()) {
            // add throw Exception
            return null;
        }
        else {
            User userEntity = userMapper.toEntity(userDto);
            userRepo.save(userEntity);
            return userMapper.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(long id) {
        User userEntity = userRepo.findById(id);
        if (userEntity==null)
            // add throw Exception
            return null;
        else {
            return userMapper.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        List<User> users = userRepo.findAll();
        if (users == null)
            // add throw Exception
            return null;
        else {
            return userMapper.toDto(users);
        }
    }






}
