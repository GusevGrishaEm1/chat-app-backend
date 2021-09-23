package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Role;
import ru.example.webapp.domain.dto.MessageDto;
import ru.example.webapp.domain.dto.MessageDtoRequest;
import ru.example.webapp.domain.dto.RoomDto;
import ru.example.webapp.domain.dto.UserDto;
import ru.example.webapp.exception.MessageNotFoundException;
import ru.example.webapp.exception.UserAccessException;
import ru.example.webapp.mapper.MessageMapper;
import ru.example.webapp.repository.MessageRepo;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    MessageMapper messageMapper;

    @Transactional
    public MessageDto addMessage(MessageDtoRequest messageDtoRequest) {
        Message messageEntity = messageMapper.toEntity(messageDtoRequest);
        messageRepo.save(messageEntity);
        return messageMapper.toDto(messageEntity);
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
        return messageMapper.toDto(messageEntity);
    }

    @Transactional(readOnly = true)
    public MessageDto getMessage(long id) throws MessageNotFoundException {
        Message messageEntity = messageRepo.findById(id);
        if (messageEntity == null)
            throw new MessageNotFoundException("Message with id " + id + " not found");
        else {
            return messageMapper.toDto(messageEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<MessageDto> getMessages() throws MessageNotFoundException {
        List<Message> messages = messageRepo.findAll();
        if (messages.isEmpty())
            throw new MessageNotFoundException("MessageList is empty");
        else {
            return messageMapper.toDto(messages);
        }
    }

    public MessageDto sendMessage(MessageDtoRequest messageDtoRequest,
                                  UserDto userDto,
                                  RoomDto roomDto) throws UserAccessException {
        if (!userDto.isBanned()) {
            messageDtoRequest.setUser(userDto);
            messageDtoRequest.setRoom(roomDto);
            messageDtoRequest.setDateOfSend(LocalDateTime.now());
            //add parser for messages to find commands
            messageDtoRequest.setCommand(false);
            return addMessage(messageDtoRequest);
        } else {
            throw new UserAccessException("User " + userDto.getUsername() + " does not have permission to do this");
        }
    }

    public long removeMessage(long id, UserDto userDto) throws UserAccessException, MessageNotFoundException {
        if (userDto.getRole() == Role.ADMIN || userDto.getRole() == Role.MODERATOR) {
            return deleteMessage(id);
        } else {
            throw new UserAccessException("User "+ userDto.getUsername() +  " does not have permission to do this");
        }
    }

}
