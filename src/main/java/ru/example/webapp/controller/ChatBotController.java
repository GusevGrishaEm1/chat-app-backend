package ru.example.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.webapp.domain.dto.UserInRoomAndMessageDto;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UniqueUsernameException;
import ru.example.webapp.exception.UserAccessException;
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
    public ResponseEntity<?> parseMessage(@RequestBody UserInRoomAndMessageDto userInRoomAndMessage) throws
            RoomNotFoundException,
            UniqueUsernameException,
            IOException,
            UserAccessException
    {
       return chatBotService.parseCommand(userInRoomAndMessage.getMessage(), userInRoomAndMessage.getUserInRoom());
    }


}
