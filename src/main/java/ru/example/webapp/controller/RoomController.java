package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.service.RoomService;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

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

    @PostMapping("/create/room")
    public RoomDto createRoom(@RequestBody RoomDtoRequest roomDtoRequest,
                              @RequestBody UserDto userDto,
                              @RequestBody UserInRoomDtoRequest userInRoomDto) throws UserAccessException {
        return roomService.createRoom(roomDtoRequest, userDto, userInRoomDto);
    }

    @PostMapping("/create/privateRoom")
    public RoomDto createPrivateRoom(@RequestBody RoomDtoRequest roomDtoRequest,
                                     @RequestBody UserDto userDtoFirst,
                                     @RequestBody UserInRoomDtoRequest userInRoomDtoRequestFirst,
                                     @RequestBody UserDto userDtoSecond,
                                     @RequestBody UserInRoomDtoRequest userInRoomDtoRequestSecond) throws UserAccessException {
            return roomService.createPrivateRoom(roomDtoRequest, userDtoFirst, userInRoomDtoRequestFirst, userDtoSecond, userInRoomDtoRequestSecond);
    }

    @PostMapping("/invite/userInRoom")
    public UserInRoomDto inviteUserInRoom(@RequestBody UserInRoomDto whoInvite,
                                          @RequestBody UserInRoomDtoRequest whoIsInvited,
                                          @RequestBody RoomDto roomDto,
                                          @RequestBody UserDto userDto) throws UserAccessException {
        return roomService.inviteUserInRoom(whoInvite, whoIsInvited, roomDto, userDto);
    }

    @PostMapping("/disconnect/userInRoom")
    public UserInRoomDto disconnectUserInRoom(@RequestBody UserInRoomDto whoDisconnect,
                                              @RequestBody UserInRoomDto whoIsDisconnected,
                                              @RequestBody DiscInfoDtoRequest discInfoDtoRequest) throws UserAccessException {
        return roomService.disconnectUserFromRoom(whoDisconnect, whoIsDisconnected,discInfoDtoRequest);
    }

    @PutMapping("/rename/room")
    public RoomDto renameRoom(@RequestBody UserInRoomDto userInRoomDto, @RequestParam String newName) throws UserAccessException {
        return roomService.renameRoom(userInRoomDto, newName);
    }
}
