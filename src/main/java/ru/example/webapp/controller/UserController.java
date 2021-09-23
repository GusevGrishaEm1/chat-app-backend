package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.domain.dto.UserDtoRequest;
import ru.example.webapp.exception.*;
import ru.example.webapp.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

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
    public UserDto addStatusModerator(@RequestBody UserDto whoGives,
                                      @RequestBody UserDto whoIsGiven) throws UniqueUsernameException, UserAccessException {
        return userService.setModeratorStatus(whoGives, whoIsGiven);
    }

    @PutMapping("/delete/moderator")
    public UserDto deleteStatusModerator(@RequestBody UserDto whoGives,
                                         @RequestBody UserDto whoIsGiven) throws UniqueUsernameException, UserAccessException {
        return userService.removeModeratorStatus(whoGives, whoIsGiven);
    }

    @PutMapping("/unban")
    public UserDto banUser(@RequestBody UserDto whoBans,
                           @RequestBody UserDto whoIsBanned,
                           @RequestBody BanInfoDtoRequest banInfoDtoRequest) throws UniqueUsernameException, UserAccessException {
        return userService.banUser(whoBans, whoIsBanned, banInfoDtoRequest);
    }

    @PutMapping("/ban")
    public UserDto unbanUser(@RequestBody UserDto whoBans,
                             @RequestBody UserDto whoIsBanned) throws UniqueUsernameException, UserAccessException, BanInfoNotFoundException {
        return userService.unbanUser(whoBans, whoIsBanned);
    }

}
