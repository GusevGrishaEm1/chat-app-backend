package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.domain.dto.room.*;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.service.RoomService;
import java.util.List;

@RestController
@RequestMapping("/rooms")
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

    @PostMapping("/create/room")
    public RoomDto createRoom(@RequestBody CreateRoomDto createRoomDto) throws UserAccessException {
        return roomService.createRoom(createRoomDto.getRoomDtoRequest(),
                createRoomDto.getUserDto(),
                createRoomDto.getUserInRoomDtoRequest()
        );
    }

    @PostMapping("/create/privateRoom")
    public RoomDto createPrivateRoom(@RequestBody CreatePrivateRoomDto createPrivateRoomDto) throws UserAccessException {
            return roomService.createPrivateRoom(
                    createPrivateRoomDto.getRoomDtoRequest(),
                    createPrivateRoomDto.getUserDtoFirst(),
                    createPrivateRoomDto.getUserInRoomDtoRequestFirst(),
                    createPrivateRoomDto.getUserDtoSecond(),
                    createPrivateRoomDto.getUserInRoomDtoRequestSecond()
            );
    }

    @PostMapping("/invite/userInRoom")
    public UserInRoomDto inviteUserInRoom(@RequestBody InviteUserInRoomDto inviteUserInRoomDto) throws UserAccessException {
        return roomService.inviteUserInRoom(
                inviteUserInRoomDto.getWhoInvite(),
                inviteUserInRoomDto.getWhoIsInvited(),
                inviteUserInRoomDto.getRoomDto(),
                inviteUserInRoomDto.getUserDto()
        );
    }

    @PostMapping("/disconnect/userInRoom")
    public UserInRoomDto disconnectUserInRoom(@RequestBody DisconnectUserFromRoomDto disconnectUserFromRoomDto) throws UserAccessException {
        return roomService.disconnectUserFromRoom(
                disconnectUserFromRoomDto.getWhoDisconnect(),
                disconnectUserFromRoomDto.getWhoIsDisconnected(),
                disconnectUserFromRoomDto.getDiscInfoDtoRequest()
        );
    }

    @PutMapping("/rename/room")
    public RoomDto renameRoom(@RequestBody RenameRoomDto renameRoomDto) throws UserAccessException {
        return roomService.renameRoom(
                renameRoomDto.getUserInRoomDto(),
                renameRoomDto.getNewName()
        );
    }
}
