package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.*;
import ru.example.webapp.domain.dto.MessageSenderDto;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;
import ru.example.webapp.exception.*;
import ru.example.webapp.mapper.MessageMapper;
import ru.example.webapp.mapper.RoomMapper;
import ru.example.webapp.mapper.UserMapper;
import ru.example.webapp.repository.MessageRepo;
import ru.example.webapp.repository.RoomRepo;
import ru.example.webapp.repository.UserRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private MessageRepo messageRepo;
    private UserService userService;
    private UserInRoomService userInRoomService;
    private UserRepo userRepo;
    private RoomRepo roomRepo;

    private MessageMapper messageMapper;
    private UserMapper userMapper;
    private RoomMapper roomMapper;

    @Autowired
    public MessageService(
            MessageRepo messageRepo,
            UserService userService,
            UserInRoomService userInRoomService,
            UserRepo userRepo,
            RoomRepo roomRepo)
    {
        this.messageRepo = messageRepo;
        this.userService = userService;
        this.userInRoomService = userInRoomService;
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
    }

    @Transactional
    public MessageDto add(MessageDtoRequest newMessage) throws RoomNotFoundException, UserNotFoundException {
        Message messageEntity = messageMapper.INSTANCE.toEntity(newMessage);
        messageEntity.setDateOfSend(LocalDateTime.now());
        User user = userRepo.findById(newMessage.getUserId());
        Room room = roomRepo.findById(newMessage.getRoomId());
        messageEntity.setUser(user);
        messageEntity.setRoom(room);
        messageRepo.save(messageEntity);
        return messageMapper.INSTANCE.toDto(messageEntity);
    }

    public long delete(long id) throws MessageNotFoundException {
        if (messageRepo.findById(id) == null)
            throw new MessageNotFoundException("Message with id " + id + " not found");
        else {
            messageRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public MessageDto edit(MessageDto message) {
        Message messageEntity = messageRepo.findById(message.getId());
        messageEntity.setText(message.getText());
        messageRepo.save(messageEntity);
        return messageMapper.INSTANCE.toDto(messageEntity);
    }

    @Transactional(readOnly = true)
    public MessageDto get(long id) throws MessageNotFoundException {
        Message messageEntity = messageRepo.findById(id);
        if (messageEntity == null)
            throw new MessageNotFoundException("Message with id " + id + " not found");
        else {
            return messageMapper.INSTANCE.toDto(messageEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<MessageDto> getMessages() throws MessageNotFoundException {
        List<Message> messages = messageRepo.findAll();
        if (messages.isEmpty())
            throw new MessageNotFoundException("MessageList is empty");
        else {
            return messageMapper.INSTANCE.toDto(messages);
        }
    }

    public MessageDto sendMessage(String text, long userInRoomId) throws
            UserAccessException,
            UserNotFoundException,
            RoomNotFoundException,
            UserInRoomNotFoundException
    {
        UserInRoomDto userInRoom = userInRoomService.getUserInRoom(userInRoomId);
        UserDto user = userService.getUser(userInRoom.getUserId());
        if (!user.isBanned() && !userInRoom.isDisconnected()) {
            MessageDtoRequest message = new MessageDtoRequest();
            message.setText(text);
            message.setUserId(userInRoom.getUserId());
            message.setRoomId(userInRoom.getRoomId());
            return add(message);
        } else {
            throw new UserAccessException("User " + user.getUsername() + " does not have permission to do this");
        }
    }

    public long removeMessage(long messageId, long userInRoomId) throws
            UserAccessException,
            MessageNotFoundException,
            UserInRoomNotFoundException,
            UserNotFoundException
    {
        UserInRoomDto userInRoom = userInRoomService.getUserInRoom(userInRoomId);
        UserDto user = userService.getUser(userInRoom.getUserId());
        if ((user.getRole() == Role.ADMIN || user.getRole() == Role.MODERATOR) && (!userInRoom.isDisconnected())) {
            return delete(messageId);
        } else {
            throw new UserAccessException("User "+ user.getUsername() +  " does not have permission to do this");
        }
    }

    @Transactional
    public MessageDto processingMessage(MessageSenderDto message) throws UserNotFoundException, RoomNotFoundException {
        MessageDtoRequest messageDtoRequest = new MessageDtoRequest();
        messageDtoRequest.setText(message.getValue());
        messageDtoRequest.setDateOfSend(LocalDateTime.now());
        messageDtoRequest.setUserId(message.getUserId());
        messageDtoRequest.setRoomId(message.getRoomId());
        return add(messageDtoRequest);
    }

}
