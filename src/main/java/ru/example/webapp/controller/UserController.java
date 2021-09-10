package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.UserDto;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping()
    public List<UserDto> getListUser() {
        return null;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteUser(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public UserDto addUser(@RequestBody UserDto user) {
        return null;
    }

    @PutMapping("/{id}")
    public UserDto editUser(@RequestBody UserDto user, @PathVariable Long id) {
        return null;
    }

}
