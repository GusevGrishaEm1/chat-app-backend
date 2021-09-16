package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.dto.MessageDto;
import ru.example.webapp.domain.dto.MessageDtoRequest;
import java.util.List;

@Mapper
public interface MessageMapper {

    MessageDto toDto(Message message);

    List<MessageDto> toDto(List<Message> messages);

    Message toEntity(MessageDto message);

    Message toEntity(MessageDtoRequest Room);

}
