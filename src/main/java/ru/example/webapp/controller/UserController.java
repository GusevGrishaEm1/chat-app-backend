package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.domain.dto.UserDtoRequest;
import ru.example.webapp.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public List<UserDto> getListUser() {
        return  userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public long deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDtoRequest user) {
        return userService.addUser(user);
    }

    @PutMapping()
    public UserDto editUser(@RequestBody UserDto user) {
        return userService.editUser(user);
    }

}
