package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.Type;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.UserInRoomDto;

import java.util.List;

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
