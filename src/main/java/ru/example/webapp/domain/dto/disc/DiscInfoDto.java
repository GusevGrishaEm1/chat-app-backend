package ru.example.webapp.domain.dto.disc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscInfoDto {

    private long id;

    private int minutes;

    private LocalDateTime dateOfDisc;
}
