package ru.example.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.dto.MessageDto;
import ru.example.webapp.domain.dto.MessageDtoRequest;
import ru.example.webapp.mapper.MessageMapper;
import ru.example.webapp.repository.MessageRepo;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    MessageMapper messageMapper;

    @Transactional
    public MessageDto addMessage(MessageDtoRequest roomDtoRequest) {
        Message messageEntity = messageMapper.toEntity(roomDtoRequest);
        messageRepo.save(messageEntity);
        return messageMapper.toDto(messageEntity);
    }

    public long deleteMessage(long id) {
        if (messageRepo.findById(id) == null)
            // add throw Exception
            return -1;
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
    public MessageDto getMessage(long id) {
        Message messageEntity = messageRepo.findById(id);
        if (messageEntity==null)
            // add throw Exception
            return null;
        else {
            return messageMapper.toDto(messageEntity);
        }
    }

    @Transactional(readOnly = true)
    public List<MessageDto> getMessages() {
        List<Message> messages = messageRepo.findAll();
        if (messages == null)
            // add throw Exception
            return null;
        else {
            return messageMapper.toDto(messages);
        }
    }
}
