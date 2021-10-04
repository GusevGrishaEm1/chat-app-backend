package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.user.*;
import ru.example.webapp.domain.dto.auth.UsernamePasswordDtoRequest;
import ru.example.webapp.exception.*;
import ru.example.webapp.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
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

    @PutMapping("/add/moderator")
    public UserDto addStatusModerator(@RequestBody SetModeratorStatusDto setModeratorStatusDto) throws UniqueUsernameException, UserAccessException {
        return userService.setModeratorStatus(
                setModeratorStatusDto.getWhoGives(),
                setModeratorStatusDto.getWhoIsGiven()
        );
    }

    @PutMapping("/delete/moderator")
    public UserDto deleteStatusModerator(@RequestBody RemoveModeratorStatusDto removeModeratorStatusDto) throws UniqueUsernameException, UserAccessException {
        return userService.removeModeratorStatus(
                removeModeratorStatusDto.getWhoRemoves(),
                removeModeratorStatusDto.getWhoIsRemoved()
        );
    }

    @PutMapping("/unban")
    public UserDto banUser(@RequestBody BanUserDto banUserDto) throws UniqueUsernameException, UserAccessException {
        return userService.banUser(
                banUserDto.getWhoBans(),
                banUserDto.getWhoIsBanned(),
                banUserDto.getBanInfoDtoRequest()
        );
    }

    @PutMapping("/ban")
    public UserDto unbanUser(@RequestBody UnbanUserDto unbanUserDto) throws UniqueUsernameException, UserAccessException, BanInfoNotFoundException {
        return userService.unbanUser(
                unbanUserDto.getWhoUnbans(),
                unbanUserDto.getWhoIsUnbanned()
        );
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
