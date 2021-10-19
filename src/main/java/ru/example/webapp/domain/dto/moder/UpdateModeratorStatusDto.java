package ru.example.webapp.domain.dto.moder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateModeratorStatusDto {
    private long userInRoomId;
    private String username;
}
