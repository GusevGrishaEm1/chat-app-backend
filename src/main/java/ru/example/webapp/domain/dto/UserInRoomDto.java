package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInRoomDto {

    private long id;

    private boolean owner;

    private boolean disconnected;

    private DiscInfo discInfo;

    private RoomDto room;

    private UserDto user;

}
