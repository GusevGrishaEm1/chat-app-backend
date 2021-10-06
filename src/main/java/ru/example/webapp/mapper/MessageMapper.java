package ru.example.webapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;
import java.util.List;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper( MessageMapper.class );

    MessageDto toDto(Message message);

    List<MessageDto> toDto(List<Message> messages);

    Message toEntity(MessageDto message);

    Message toEntity(MessageDtoRequest Room);

}
