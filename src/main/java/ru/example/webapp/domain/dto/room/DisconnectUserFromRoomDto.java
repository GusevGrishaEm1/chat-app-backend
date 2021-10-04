package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.DiscInfoDtoRequest;
import ru.example.webapp.domain.dto.UserInRoomDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisconnectUserFromRoomDto {
    private UserInRoomDto whoDisconnect;
    private UserInRoomDto whoIsDisconnected;
    private DiscInfoDtoRequest discInfoDtoRequest;
}
