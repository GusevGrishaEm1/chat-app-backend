package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.Type;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDtoRequest {

    private String name;

    private Type type;

    private boolean privateRoom;

    private List<MessageDto> listMessage;

    private List<UserInRoomDto> listUserInRoom;
}
