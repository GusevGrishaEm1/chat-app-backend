package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.UserInRoomDto;
import java.util.List;

@RestController
@RequestMapping("/userInRoom")
public class UserInRoomController {

    @GetMapping()
    public List<UserInRoomDto> getListUserInRoom() {
        return null;
    }

    @GetMapping("/{id}")
    public UserInRoomDto getUserInRoom(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteUserInRoom(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public UserInRoomDto addUserInRoom(@RequestBody UserInRoomDto userInRoom) {
        return null;
    }

    @PutMapping("/{id}")
    public UserInRoomDto editUserInRoom(@RequestBody UserInRoomDto userInRoom, @PathVariable Long id) {
        return null;
    }

}
