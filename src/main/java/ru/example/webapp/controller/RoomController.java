package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.room.*;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.exception.UserInRoomNotFoundException;
import ru.example.webapp.exception.UserNotFoundException;
import ru.example.webapp.service.RoomService;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<RoomDto> getListRoom() throws RoomNotFoundException {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable long id) throws RoomNotFoundException {
        return roomService.getRoom(id);
    }

    @DeleteMapping("/{id}")
    public long deleteRoom(@PathVariable long id) throws RoomNotFoundException {
        return roomService.deleteRoom(id);
    }

    @PostMapping()
    public RoomDto addRoom(@RequestBody RoomDtoRequest room) {
        return roomService.addRoom(room);
    }

    @PutMapping()
    public RoomDto editRoom(@RequestBody RoomDto room) {
        return roomService.editRoom(room);
    }

    @PostMapping("/create/public")
    public RoomDto createRoom(@RequestBody CreatePublicRoomDto publicRoom) throws
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return roomService.createRoom(publicRoom.getRoomName(), publicRoom.getUserInRoomId());
    }

    @PostMapping("/create/private")
    public RoomDto createPrivateRoom(@RequestBody CreatePrivateRoomDto privateRoom) throws
            UserAccessException,
            UserNotFoundException
    {
            return roomService.createPrivateRoom(privateRoom.getFirstUserId(), privateRoom.getSecondUserId());
    }

    @PostMapping("/connect")
    public UserInRoomDto connectUser(@RequestBody ConnectUserDto connectUser) throws
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return roomService.connectUser(
                connectUser.getUserInRoomId(),
                connectUser.getUsername(),
                connectUser.getRoomName()
        );
    }

    @PostMapping("/disconnect")
    public UserInRoomDto disconnectUser(@RequestBody DisconnectUserDto disconnectUser) throws
            UserNotFoundException,
            UserInRoomNotFoundException,
            UserAccessException
    {
        return roomService.disconnectUser(
                disconnectUser.getUserInRoomId(),
                disconnectUser.getUsername(),
                disconnectUser.getRoomName(),
                disconnectUser.getMinutes()
        );
    }

    @PutMapping("/rename")
    public RoomDto renameRoom(@RequestParam RenameRoomDto renameRoom) throws
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException,
            RoomNotFoundException
    {
        return roomService.renameRoom(renameRoom.getUserInRoomDto(),renameRoom.getNewName(), renameRoom.getOldName());
    }
}
