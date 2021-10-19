package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.ban.BanUserDto;
import ru.example.webapp.domain.dto.ban.UnbanUserDto;
import ru.example.webapp.domain.dto.moder.UpdateModeratorStatusDto;
import ru.example.webapp.domain.dto.user.*;
import ru.example.webapp.domain.dto.auth.UsernamePasswordDtoRequest;
import ru.example.webapp.exception.*;
import ru.example.webapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserDto> getListUser() throws UserNotFoundException {
        return  userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) throws UserNotFoundException  {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public long deleteUser(@PathVariable long id) throws UserNotFoundException  {
        return userService.deleteUser(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDtoRequest user) throws UniqueUsernameException {
        return userService.addUser(user);
    }

    @PutMapping()
    public UserDto editUser(@RequestBody UserDto user) throws UniqueUsernameException {
        return userService.editUser(user);
    }

    @PutMapping("/mod")
    public UserDto addStatusModerator(@RequestBody UpdateModeratorStatusDto moderatorStatus) throws
            UniqueUsernameException,
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return userService.setModeratorStatus(moderatorStatus.getUserInRoomId(), moderatorStatus.getUsername());
    }

    @PutMapping("/unmod")
    public UserDto deleteStatusModerator(@RequestBody UpdateModeratorStatusDto moderatorStatus) throws
            UniqueUsernameException,
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return userService.deleteModeratorStatus(moderatorStatus.getUserInRoomId(), moderatorStatus.getUsername());
    }

    @PutMapping("/unban")
    public UserDto banUser(@RequestBody BanUserDto banUser) throws
            UniqueUsernameException,
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return userService.banUser(banUser.getUserInRoomId(), banUser.getUsername(), banUser.getMinutes());
    }

    @PutMapping("/ban")
    public UserDto unbanUser(@RequestBody UnbanUserDto unbanUser) throws
            UniqueUsernameException,
            UserAccessException,
            BanInfoNotFoundException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return userService.unbanUser(unbanUser.getUserInRoomId(), unbanUser.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsernamePasswordDtoRequest request) throws UserNotFoundException {
        return userService.login(request);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) {
        userService.logout(httpServletRequest, httpServletResponse);
    }

    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody UsernamePasswordDtoRequest request) throws UniqueUsernameException {
        return userService.registration(request);
    }

}
