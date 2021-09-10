package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.RoomDto;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @GetMapping()
    public List<RoomDto> getListRoom() {
        return null;
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteRoom(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public RoomDto addRoom(@RequestBody RoomDto room) {
        return null;
    }

    @PutMapping("/{id}")
    public RoomDto editRoom(@RequestBody RoomDto room, @PathVariable Long id) {
        return null;
    }

}
