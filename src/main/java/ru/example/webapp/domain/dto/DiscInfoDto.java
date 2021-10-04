package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscInfoDto {

    private long id;

    private int minutes;

    private LocalDateTime dateOfDisc;

    private UserInRoomDto userInRoom;

}
