package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDtoRequest {

    private String name;

    private Type type;

    private boolean privateRoom;

}
