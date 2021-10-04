package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.UserInRoomDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteUserInRoomDto {
    private UserInRoomDto whoInvite;
    private UserInRoomDtoRequest whoIsInvited;
    private RoomDto roomDto;
    private UserDto userDto;
}
