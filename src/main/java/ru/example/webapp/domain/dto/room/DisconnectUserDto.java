package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.webapp.domain.dto.disc.DiscInfoDtoRequest;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisconnectUserDto {
    private long userInRoomId;
    private String username;
    private String roomName;
    private int minutes;
}
