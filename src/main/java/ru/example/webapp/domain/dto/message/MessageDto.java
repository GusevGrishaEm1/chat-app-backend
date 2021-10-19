package ru.example.webapp.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private long id;

    private String text;

    private LocalDateTime dateOfSend;

    private long userId;

    private long roomId;
}
