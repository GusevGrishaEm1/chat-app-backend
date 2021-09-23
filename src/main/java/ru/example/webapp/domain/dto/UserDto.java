package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    private String username;

    private String password;

    private boolean banned;

    private Role role;

    private BanInfoDto banInfo;

    private List<MessageDto> listMessage;

    private List<UserInRoomDto> listUserInRoom;

}
