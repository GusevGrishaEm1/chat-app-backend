package ru.example.webapp.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.MessageDto;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @GetMapping()
    public List<MessageDto> getListMessage() {
        return null;
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Long deleteMessage(@PathVariable long id) {
        return null;
    }

    @PostMapping()
    public MessageDto addMessage(@RequestBody MessageDto message) {
        return null;
    }

    @PutMapping("/{id}")
    public MessageDto editMessage(@RequestBody MessageDto message, @PathVariable Long id) {
        return null;
    }

}
