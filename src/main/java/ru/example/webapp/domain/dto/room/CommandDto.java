package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandDto {
    private long userInRoomId;
    private String command;
}
