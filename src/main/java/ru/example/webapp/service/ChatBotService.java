package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.webapp.exception.*;
import java.io.IOException;

@Service
public class ChatBotService {

    private final CommandsService commandsService;
    private final UserService userService;
    private final RoomService roomService;
    private final MessageService messageService;
    private final  YoutubeService youtubeService;

    @Autowired
    public ChatBotService(UserService userService,
                          RoomService roomService,
                          MessageService messageService,
                          YoutubeService youtubeService)
    {
        this.userService = userService;
        this.roomService = roomService;
        this.messageService = messageService;
        this.youtubeService = youtubeService;
        this.commandsService = new CommandsService();
    }

    public ResponseEntity<?> sendCommand(String text, long userInRoomId) throws
            UserAccessException,
            RoomNotFoundException,
            UniqueUsernameException,
            IOException,
            UserNotFoundException,
            UserInRoomNotFoundException
    {
        String[] temp = text.split("[{}]+");
        switch (temp[0]) {
            case ("//room create "):
                roomService.createRoom(temp[1], userInRoomId);
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//room remove "):
                roomService.deleteRoomByName(temp[1]);
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//room rename "):
                roomService.renameRoom(userInRoomId, temp[1], temp[3]);
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//room connect "):
                roomService.connectUser(userInRoomId, temp[1], temp[3]);
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//room disconnect "):
                roomService.disconnectUser(userInRoomId, temp[1], temp[3], Integer.parseInt(temp[5]));
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//user rename "):
                userService.renameUser(userInRoomId, temp[1], temp[3]);
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//user ban "):
                userService.banUser(userInRoomId, temp[1], Integer.parseInt(temp[3]));
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//user moderator "):
                if(temp[2].contains("-n")) userService.setModeratorStatus(userInRoomId, temp[1]);
                else {
                    userService.deleteModeratorStatus(userInRoomId, temp[1]);
                }
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
            case ("//yBot find -v -l "):
                return new ResponseEntity<>(youtubeService.findVideo(temp[1], temp[3]), HttpStatus.OK);
            case ("//yBot help"):
                return new ResponseEntity<>(commandsService.getListYBotCommands(), HttpStatus.OK);
            case ("//help"):
                return new ResponseEntity<>(commandsService.getListAllCommands(), HttpStatus.OK);
            case ("//channelInfo "):
                return new ResponseEntity<>(youtubeService.findChannel(temp[1]), HttpStatus.OK);
            case ("//videoCommentRandom "):
                return new ResponseEntity<>(youtubeService.findRandomComment(temp[1], temp[3]), HttpStatus.OK);
            default:
                return new ResponseEntity<>(messageService.sendMessage(text, userInRoomId), HttpStatus.OK);
        }
    }

}
