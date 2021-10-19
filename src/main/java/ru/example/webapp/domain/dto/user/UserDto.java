package ru.example.webapp.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.*;

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
