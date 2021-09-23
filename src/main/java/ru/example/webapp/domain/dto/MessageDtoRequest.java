package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDtoRequest {

    private String value;

    private LocalDateTime dateOfSend;

    private boolean command;

    private UserDto user;

    private RoomDto room;
}
