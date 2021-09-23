package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.domain.dto.UserDtoRequest;
import ru.example.webapp.exception.*;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.UserRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BanInfoService banInfoService;

    UserMapper userMapper;

    @Transactional
    public UserDto addUser(UserDtoRequest userDtoRequest) throws UniqueUsernameException {
        User userFromDb = userRepo.findByUsername(userDtoRequest.getUsername());
        if (userFromDb != null) {
            throw new UniqueUsernameException("Username " + userDtoRequest.getUsername() + " is taken");
        } else {
            User userEntity = userMapper.toEntity(userDtoRequest);
            userRepo.save(userEntity);
            return userMapper.toDto(userEntity);
        }
    }

    public long deleteUser(long id) throws UserNotFoundException {
        if (userRepo.findById(id) == null)
            throw new UserNotFoundException("User with " + id + " not found");
        else {
            userRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public UserDto editUser(UserDto userDto) throws UniqueUsernameException {
        User userFromDb = userRepo.findByUsername(userDto.getUsername());
        if (userFromDb != null && userFromDb.getId() != userDto.getId()) {
            throw new UniqueUsernameException("Username {userDto.getUsername()} is taken");
        } else {
            User userEntity = userMapper.toEntity(userDto);
            userRepo.save(userEntity);
            return userMapper.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(long id) throws UserNotFoundException {
        User userEntity = userRepo.findById(id);
        if (userEntity == null)
            throw new UserNotFoundException("User with " + id + " not found");
        else {
            return userMapper.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsers() throws UserNotFoundException {
        List<User> users = userRepo.findAll();
        if (users.isEmpty())
            throw new UserNotFoundException("UserList is empty");
        else {
            return userMapper.toDto(users);
        }
    }

    public UserDto setModeratorStatus(UserDto whoGives, UserDto whoIsGiven) throws UserAccessException, UniqueUsernameException {
        if (whoGives.getRole() == Role.ADMIN) {
            whoIsGiven.setRole(Role.MODERATOR);
            return editUser(whoIsGiven);
        } else {
            throw new UserAccessException("User " + whoGives.getUsername() + " does not have permission to do this");
        }
    }

    public UserDto removeModeratorStatus(UserDto whoRemoves,
                                         UserDto whoIsRemoved) throws UserAccessException, UniqueUsernameException {
        if (whoRemoves.getRole() == Role.ADMIN) {
            whoIsRemoved.setRole(Role.USER);
            return editUser(whoIsRemoved);
        } else {
            throw new UserAccessException("User " + whoRemoves.getUsername() + " does not have permission to do this");
        }
    }

    public UserDto banUser(UserDto whoBans,
                           UserDto whoIsBanned,
                           BanInfoDtoRequest banInfoDtoRequest) throws UniqueUsernameException, UserAccessException {
        if (whoBans.getRole() == Role.ADMIN || whoBans.getRole() == Role.MODERATOR) {
            whoIsBanned.setBanned(true);
            banInfoDtoRequest.setDateOfBan(LocalDateTime.now());
            banInfoDtoRequest.setUser(whoIsBanned);
            banInfoService.addBanInfo(banInfoDtoRequest);
            return editUser(whoIsBanned);
        } else {
            throw new UserAccessException("User " + whoBans.getUsername() + " does not have permission to do this");
        }
    }

    public UserDto unbanUser(UserDto whoUnbans,
                             UserDto whoIsUnbanned) throws UserAccessException, UniqueUsernameException, BanInfoNotFoundException {
        if (whoUnbans.getRole() == Role.ADMIN || whoUnbans.getRole() == Role.MODERATOR) {
            whoIsUnbanned.setBanned(false);
            banInfoService.deleteBanInfo(whoIsUnbanned.getId());
            return editUser(whoIsUnbanned);
        } else {
            throw new UserAccessException("User " + whoUnbans.getUsername() + " does not have permission to do this");
        }
    }


}
