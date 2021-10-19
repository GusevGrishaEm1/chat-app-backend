package ru.example.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.example.webapp.domain.dto.UserInRoomAndMessageDto;
import ru.example.webapp.domain.dto.room.CommandDto;
import ru.example.webapp.exception.*;
import ru.example.webapp.service.ChatBotService;
import java.io.IOException;

@RestController
@RequestMapping("/chatBot")
public class ChatBotController {

    private ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @PostMapping
    public ResponseEntity<?> parseMessage(@RequestBody CommandDto command) throws
            RoomNotFoundException,
            UniqueUsernameException,
            IOException,
            UserAccessException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
       return chatBotService.sendCommand(command.getCommand(),command.getUserInRoomId());
    }


}
