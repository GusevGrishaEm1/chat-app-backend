package ru.example.webapp.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.*;
import ru.example.webapp.domain.dto.BanInfoDto;
import ru.example.webapp.domain.dto.message.MessageDto;
import ru.example.webapp.domain.dto.UserInRoomDto;

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

}