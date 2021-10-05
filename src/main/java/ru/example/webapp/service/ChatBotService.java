package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.webapp.domain.Type;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.room.RoomDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.video.VideoDto;
import ru.example.webapp.exception.RoomNotFoundException;
import ru.example.webapp.exception.UniqueUsernameException;
import ru.example.webapp.exception.UserAccessException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatBotService {

    private final CommandsService commandsService;
    private final UserService userService;
    private final RoomService roomService;
    private final MessageService messageService;
    private final  UserInRoomService userInRoomService;
    private final  YoutubeService youtubeService;

    @Autowired
    public ChatBotService(UserService userService,
                          RoomService roomService,
                          MessageService messageService,
                          UserInRoomService userInRoomService,
                          CommandsService commandsService,
                          YoutubeService youtubeService)
    {
        this.commandsService = commandsService;
        this.userService = userService;
        this.roomService = roomService;
        this.messageService = messageService;
        this.userInRoomService = userInRoomService;
        this.youtubeService = youtubeService;
    }

    public ResponseEntity<?> parseCommand(MessageDtoRequest message, UserInRoomDto userInRoom) throws
            UserAccessException,
            RoomNotFoundException,
            UniqueUsernameException, IOException {
        return parseMessage(message, userInRoom);
    }

    private ResponseEntity<?> parseMessage(MessageDtoRequest message, UserInRoomDto userInRoom) throws
            UserAccessException,
            RoomNotFoundException,
            UniqueUsernameException, IOException {
        String command = message.getValue().split("[\\{\\}]+")[0];
        String temp1, temp2, temp3;
        RoomDto room;
        UserDto user;
        UserInRoomDtoRequest userInRoom2;
        UserInRoomDto userInRoom3;
        Map<Object, Object> response = new HashMap<>();
        switch (command) {
            case ("//room create "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                RoomDtoRequest roomDtoRequest = new RoomDtoRequest();
                roomDtoRequest.setName(temp1);
                roomDtoRequest.setPrivateRoom(false);
                roomDtoRequest.setType(Type.USER_COMMON);
                roomService.createRoom(roomDtoRequest, userInRoom.getUser(), new UserInRoomDtoRequest());
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//room remove "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                roomService.deleteRoomByName(temp1);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//room rename "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                roomService.renameRoom(userInRoom, temp1);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//room connect "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[3];
                userInRoom2 = new UserInRoomDtoRequest();
                room = roomService.getByName(temp1);
                user = userService.getByUsername(temp2);
                roomService.inviteUserInRoom(userInRoom, userInRoom2, room, user);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//room disconnect "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[3];
                temp3 = message.getValue().split("[\\{\\}]+")[5];
                DiscInfoDtoRequest discInfo = new DiscInfoDtoRequest();
                discInfo.setMinutes(Integer.parseInt(temp3));
                room = roomService.getByName(temp1);
                user = userService.getByUsername(temp2);
                userInRoom3 = userInRoomService.getByUserAndRoom(user,room);
                roomService.disconnectUserFromRoom(userInRoom, userInRoom3, discInfo);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//user rename "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[3];
                user = userService.getByUsername(temp1);
                userService.renameUser(userInRoom.getUser(), user, temp2);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//user ban "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[3];
                BanInfoDtoRequest banInfo = new BanInfoDtoRequest();
                banInfo.setMinutes(Integer.parseInt(temp2));
                user = userService.getByUsername(temp1);
                userService.banUser(userInRoom.getUser(), user, banInfo);
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//user moderator "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[2];
                user = userService.getByUsername(temp1);
                if(temp2.contains("-n")) {
                    userService.setModeratorStatus(userInRoom.getUser(), user);
                }
                else {
                    userService.removeModeratorStatus(userInRoom.getUser(), user);
                }
                message.setCommand(true);
                return new ResponseEntity<MessageDto>(messageService.sendMessage(message, userInRoom), HttpStatus.OK);
            case ("//yBot find -v -l "):
                temp1 = message.getValue().split("[\\{\\}]+")[1];
                temp2 = message.getValue().split("[\\{\\}]+")[3];
                return new ResponseEntity<VideoDto>(youtubeService.findVideo(temp1, temp2), HttpStatus.OK);
            case ("//yBot help "):
                return new ResponseEntity<List<String>>(commandsService.getlistYBotCommands(), HttpStatus.OK);
            case ("//help"):
                return new ResponseEntity<List<String>>(commandsService.getlistAllCommands(), HttpStatus.OK);
            default:
                message.setCommand(false);
                return ResponseEntity.ok(response.put("message", messageService.sendMessage(message, userInRoom)));
        }
    }

}
