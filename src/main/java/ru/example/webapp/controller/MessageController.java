package ru.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ru.example.webapp.domain.dto.MessageSenderDto;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.domain.dto.message.RemoveMessageDto;
import ru.example.webapp.domain.dto.message.SendMessageDto;
import ru.example.webapp.exception.*;
import ru.example.webapp.service.MessageService;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping()
    public List<MessageDto> getListMessage() throws MessageNotFoundException {
        return messageService.getMessages();
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable long id) throws MessageNotFoundException {
        return messageService.get(id);
    }

    @DeleteMapping("/{id}")
    public long deleteMessage(@PathVariable long id) throws MessageNotFoundException {
        return messageService.delete(id);
    }

    @PostMapping()
    public MessageDto addMessage(@RequestBody MessageDtoRequest message) throws UserNotFoundException, RoomNotFoundException {
        return messageService.add(message);
    }

    @PutMapping()
    public MessageDto editMessage(@RequestBody MessageDto message) {
        return messageService.edit(message);
    }


    @PostMapping("/send") public MessageDto sendMessage(@RequestBody SendMessageDto message) throws
            UserAccessException,
            UserNotFoundException,
            RoomNotFoundException,
            UserInRoomNotFoundException
    {
        return messageService.sendMessage(message.getText(), message.getUserInRoomId());
    }

    @DeleteMapping("/delete") public long deleteMessage(@RequestBody RemoveMessageDto removeMessage) throws
            UserAccessException,
            MessageNotFoundException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        return messageService.removeMessage(removeMessage.getMessageId(), removeMessage.getUserInRoomId());
    }

    @MessageMapping("/chat.send")
    @SendTo("/room/public")
    public MessageDto processingMessage(@Payload MessageSenderDto chatMessage) throws UserNotFoundException, RoomNotFoundException {
        return messageService.processingMessage(chatMessage);
    }
}
