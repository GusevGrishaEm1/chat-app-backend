package ru.example.webapp.domain.dto.userInRoom;

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

    private long userId;

    private long roomId;
}
