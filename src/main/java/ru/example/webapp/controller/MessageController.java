package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.domain.dto.message.RemoveMessageDto;
import ru.example.webapp.domain.dto.message.SendMessageDto;
import ru.example.webapp.exception.MessageNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.service.MessageService;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping()
    public List<MessageDto> getListMessage() throws MessageNotFoundException {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable long id) throws MessageNotFoundException {
        return messageService.getMessage(id);
    }

    @DeleteMapping("/{id}")
    public long deleteMessage(@PathVariable long id) throws MessageNotFoundException {
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

    @PostMapping("/send") MessageDto sendMessage(@RequestBody SendMessageDto sendMessage) throws UserAccessException {
        return messageService.sendMessage(sendMessage.getMessageDtoRequest(), sendMessage.getUserInRoom());
    }

    @DeleteMapping("/delete/{id}") long deleteMessage(@RequestBody RemoveMessageDto removeMessageDto) throws UserAccessException, MessageNotFoundException {
        return messageService.removeMessage(removeMessageDto.getId(), removeMessageDto.getUserInRoom());
    }
}
