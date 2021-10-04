package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.room.RoomDto;
import ru.example.webapp.domain.dto.user.UserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInRoomDto {

    private long id;

    private boolean owner;

    private boolean disconnected;

    private DiscInfoDto discInfo;

    private RoomDto room;

    private UserDto user;

}
