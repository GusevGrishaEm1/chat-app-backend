package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanInfoDto {

    private long id;

    private int minutes;

    private LocalDateTime dateOfBan;

    private User user;

    public static BanInfoDto toDto(BanInfo banInfo) {
        BanInfoDto banInfoDto = new BanInfoDto();
        banInfoDto.setId(banInfo.getId());
        banInfoDto.setMinutes(banInfo.getMinutes());
        banInfoDto.setDateOfBan(banInfo.getDateOfBan());
        banInfoDto.setUser(banInfo.getUser());
        return banInfoDto;
    }
}
