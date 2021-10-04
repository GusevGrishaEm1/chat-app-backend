package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.dto.*;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import ru.example.webapp.exception.MessageNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.mapper.MessageMapper;
import ru.example.webapp.repository.MessageRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    private MessageMapper messageMapper;

    @Transactional
    public MessageDto addMessage(MessageDtoRequest messageDtoRequest) {
        Message messageEntity = messageMapper.INSTANCE.toEntity(messageDtoRequest);
        messageRepo.save(messageEntity);
        return messageMapper.INSTANCE.toDto(messageEntity);
    }

    public long deleteMessage(long id) throws MessageNotFoundException {
        if (messageRepo.findById(id) == null)
            throw new MessageNotFoundException("Message with id " + id + " not found");
        else {
            messageRepo.deleteById(id);
            return id;
        }
    }

    @Transactional
    public MessageDto editMessage(MessageDto messageDto) {
        Message messageEntity = messageMapper.toEntity(messageDto);
        messageRepo.save(messageEntity);
        return messageMapper.INSTANCE.toDto(messageEntity);
    }

    @Transactional(readOnly = true)
    public MessageDto getMessage(long id) throws MessageNotFoundException {
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

    public MessageDto sendMessage(MessageDtoRequest messageDtoRequest,
                                  UserInRoomDto userInRoom) throws UserAccessException
    {
        if (!userInRoom.getUser().isBanned()) {
            messageDtoRequest.setUser(userInRoom.getUser());
            messageDtoRequest.setRoom(userInRoom.getRoom());
            messageDtoRequest.setDateOfSend(LocalDateTime.now());
            return addMessage(messageDtoRequest);
        } else {
            throw new UserAccessException("User " + userInRoom.getUser().getUsername() + " does not have permission to do this");
        }
    }

    public long removeMessage(long id, UserInRoomDto userInRoom) throws UserAccessException, MessageNotFoundException {
        if (userInRoom.getUser().getRole() == Role.ADMIN || userInRoom.getUser().getRole() == Role.MODERATOR) {
            return deleteMessage(id);
        } else {
            throw new UserAccessException("User "+ userInRoom.getUser().getUsername() +  " does not have permission to do this");
        }
    }

}
