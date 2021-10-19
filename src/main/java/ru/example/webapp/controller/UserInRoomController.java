package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.service.UserInRoomService;
import java.util.List;

@RestController
@RequestMapping("/userInRoom")
public class UserInRoomController {

    @Autowired
    private UserInRoomService userInRoomService;

    @GetMapping()
    public List<UserInRoomDto> getListUserInRoom() throws UserInRoomNotFoundException {
        return userInRoomService.getListUserInRoom();
    }

    @GetMapping("/{id}")
    public UserInRoomDto getUserInRoom(@PathVariable long id) throws UserInRoomNotFoundException {
        return userInRoomService.getUserInRoom(id);
    }

    @DeleteMapping("/{id}")
    public long deleteUserInRoom(@PathVariable long id) throws UserInRoomNotFoundException {
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
