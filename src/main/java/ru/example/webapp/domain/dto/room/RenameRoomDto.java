package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RenameRoomDto {
    private long userInRoomDto;
    private String oldName;
    private String newName;

}
