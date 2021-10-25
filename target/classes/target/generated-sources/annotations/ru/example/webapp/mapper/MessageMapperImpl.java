package ru.example.webapp.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T16:17:50+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDto messageDto = new MessageDto();

        messageDto.setUserId( messageUserId( message ) );
        messageDto.setRoomId( messageRoomId( message ) );
        messageDto.setId( message.getId() );
        messageDto.setText( message.getText() );
        messageDto.setDateOfSend( message.getDateOfSend() );

        return messageDto;
    }

    @Override
    public List<MessageDto> toDto(List<Message> messages) {
        if ( messages == null ) {
            return null;
        }

        List<MessageDto> list = new ArrayList<MessageDto>( messages.size() );
        for ( Message message : messages ) {
            list.add( toDto( message ) );
        }

        return list;
    }

    @Override
    public Message toEntity(MessageDto message) {
        if ( message == null ) {
            return null;
        }

        Message message1 = new Message();

        message1.setId( message.getId() );
        message1.setText( message.getText() );
        message1.setDateOfSend( message.getDateOfSend() );

        return message1;
    }

    @Override
    public Message toEntity(MessageDtoRequest Room) {
        if ( Room == null ) {
            return null;
        }

        Message message = new Message();

        message.setText( Room.getText() );
        message.setDateOfSend( Room.getDateOfSend() );

        return message;
    }

    private long messageUserId(Message message) {
        if ( message == null ) {
            return 0L;
        }
        User user = message.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private long messageRoomId(Message message) {
        if ( message == null ) {
            return 0L;
        }
        Room room = message.getRoom();
        if ( room == null ) {
            return 0L;
        }
        long id = room.getId();
        return id;
    }
}
