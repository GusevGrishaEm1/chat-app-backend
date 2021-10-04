package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.user.UserDtoRequest;
import ru.example.webapp.domain.dto.auth.UsernamePasswordDtoRequest;
import ru.example.webapp.exception.*;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.UserRepo;
import ru.example.webapp.security.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BanInfoService banInfoService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();




    @Transactional
    public UserDto addUser(UserDtoRequest userDtoRequest) throws UniqueUsernameException {
        User userFromDb = userRepo.findByUsername(userDtoRequest.getUsername());
        if (userFromDb != null) {
            throw new UniqueUsernameException("Username " + userDtoRequest.getUsername() + " is taken");
        } else {
            User userEntity = userMapper.INSTANCE.toEntity(userDtoRequest);
            userRepo.save(userEntity);
            return userMapper.INSTANCE.toDto(userEntity);
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
            User userEntity = userMapper.INSTANCE.toEntity(userDto);
            userRepo.save(userEntity);
            return userMapper.INSTANCE.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public UserDto getUser(long id) throws UserNotFoundException {
        User userEntity = userRepo.findById(id);
        if (userEntity == null)
            throw new UserNotFoundException("User with " + id + " not found");
        else {
            return userMapper.INSTANCE.toDto(userEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsers() throws UserNotFoundException {
        List<User> users = userRepo.findAll();
        if (users.isEmpty())
            throw new UserNotFoundException("UserList is empty");
        else {
            return userMapper.INSTANCE.toDto(users);
        }
    }

    public UserDto setModeratorStatus(UserDto whoGives,
                                      UserDto whoIsGiven) throws UserAccessException, UniqueUsernameException {
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

    public ResponseEntity registration(UsernamePasswordDtoRequest request) throws UniqueUsernameException {
        UserDtoRequest user = new UserDtoRequest();
        user.setUsername(request.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setBanned(false);
        user.setRole(Role.USER);
        addUser(user);
        Map<Object, Object> response = new HashMap<>();
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        response.put("username", request.getUsername());
        response.put("token" , token);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity login(UsernamePasswordDtoRequest request) throws UserNotFoundException {
        UserDto user = getByUsername(request.getUsername());
        if(user==null) {
            throw new UserNotFoundException("User with " + request.getUsername() + " not found");
        }
        else if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new UserNotFoundException("Wrong password!!!");
        }
        else {
            Map<Object, Object> response = new HashMap<>();
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole());
            response.put("username", user.getUsername());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }

    }

    public void logout(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest,httpServletResponse,null);
    }

    public UserDto getByUsername(String username) {
        return userMapper.INSTANCE.toDto(userRepo.findByUsername(username));
    }

    public UserDto renameUser(UserDto whoRename, UserDto whoIsRenamed, String newUsername) throws UniqueUsernameException, UserAccessException {
        if(whoRename.getRole()==Role.ADMIN) {
            whoIsRenamed.setUsername(newUsername);
            return editUser(whoIsRenamed);
        }
        else {
            throw new UserAccessException("User " + whoRename.getUsername() + " does not have permission to do this");
        }
    }

}
