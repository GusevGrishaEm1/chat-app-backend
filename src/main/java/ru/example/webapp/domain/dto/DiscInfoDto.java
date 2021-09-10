package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.UserInRoom;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscInfoDto {

    private long id;

    private int minutes;

    private LocalDateTime dateOfDisc;

    private UserInRoom userInRoom;

    public static DiscInfoDto toDto(DiscInfo discInfo) {
        DiscInfoDto discInfoDto = new DiscInfoDto();
        discInfoDto.setId(discInfo.getId());
        discInfoDto.setMinutes(discInfo.getMinutes());
        discInfoDto.setDateOfDisc(discInfo.getDateOfDisc());
        discInfoDto.setUserInRoom(discInfo.getUserInRoom());
        return  discInfoDto;
    }
}
