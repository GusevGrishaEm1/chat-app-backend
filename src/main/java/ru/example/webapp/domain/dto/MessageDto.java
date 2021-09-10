package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.Message;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private long id;

    private String value;

    private LocalDateTime dateOfSend;

    private boolean command;

    private User user;

    private Room room;

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setValue(messageDto.getValue());
        messageDto.setCommand(messageDto.isCommand());
        messageDto.setUser(messageDto.getUser());
        messageDto.setRoom(message.getRoom());
        return messageDto;
    }
}
