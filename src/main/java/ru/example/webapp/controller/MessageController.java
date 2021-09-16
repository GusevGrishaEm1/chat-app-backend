package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.MessageDto;
import ru.example.webapp.domain.dto.MessageDtoRequest;
import ru.example.webapp.service.MessageService;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping()
    public List<MessageDto> getListMessage() {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable long id) {
        return messageService.getMessage(id);
    }

    @DeleteMapping("/{id}")
    public long deleteMessage(@PathVariable long id) {
        return messageService.deleteMessage(id);
    }

    @PostMapping()
    public MessageDto addMessage(@RequestBody MessageDtoRequest message) {
        return messageService.addMessage(message);
    }

    @PutMapping()
    public MessageDto editMessage(@RequestBody MessageDto message) {
        return messageService.editMessage(message);
    }

}
