package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.Type;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private long id;

    private String name;

    private Type type;

    private boolean privateRoom;

    private List<MessageDto> listMessage;

    private List<UserInRoomDto> listUserInRoom;

}
