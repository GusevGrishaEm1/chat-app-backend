package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;
import ru.example.webapp.service.UserInRoomService;
import java.util.List;

@RestController
@RequestMapping("/userInRoom")
public class UserInRoomController {

    @Autowired
    UserInRoomService userInRoomService;

    @GetMapping()
    public List<UserInRoomDto> getListUserInRoom() {
        return userInRoomService.getListUserInRoom();
    }

    @GetMapping("/{id}")
    public UserInRoomDto getUserInRoom(@PathVariable long id) {
        return userInRoomService.getUserInRoom(id);
    }

    @DeleteMapping("/{id}")
    public long deleteUserInRoom(@PathVariable long id) {
        return userInRoomService.deleteUserInRoom(id);
    }

    @PostMapping()
    public UserInRoomDto addUserInRoom(@RequestBody UserInRoomDtoRequest userInRoom) {
        return userInRoomService.addUserInRoom(userInRoom);
    }

    @PutMapping()
    public UserInRoomDto editUserInRoom(@RequestBody UserInRoomDto userInRoom) {
        return userInRoomService.editUserInRoom(userInRoom);
    }

}
