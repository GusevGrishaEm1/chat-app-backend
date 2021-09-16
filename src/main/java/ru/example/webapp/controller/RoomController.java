package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.RoomDto;
import ru.example.webapp.domain.dto.RoomDtoRequest;
import ru.example.webapp.service.RoomService;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping()
    public List<RoomDto> getListRoom() {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable long id) {
        return roomService.getRoom(id);
    }

    @DeleteMapping("/{id}")
    public long deleteRoom(@PathVariable long id) {
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

}
