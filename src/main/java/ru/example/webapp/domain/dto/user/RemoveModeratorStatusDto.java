package ru.example.webapp.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveModeratorStatusDto {
    private UserDto whoRemoves;
    private  UserDto whoIsRemoved;
}
