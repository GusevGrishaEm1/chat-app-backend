package ru.example.webapp.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.BanInfoDtoRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanUserDto {
    private UserDto whoBans;
    private UserDto whoIsBanned;
    private BanInfoDtoRequest banInfoDtoRequest;
}
