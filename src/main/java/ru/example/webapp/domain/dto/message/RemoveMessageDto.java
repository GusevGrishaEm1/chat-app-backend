package ru.example.webapp.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveMessageDto {
    private long userInRoomId;
    private long messageId;
}
