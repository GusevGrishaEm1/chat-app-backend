package ru.example.webapp.domain.dto.ban;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.user.UserDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanInfoDtoRequest {

    private int minutes;

    private long userId;
}
