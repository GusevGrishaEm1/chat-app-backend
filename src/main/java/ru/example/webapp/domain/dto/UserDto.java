package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.*;
import java.util.List;
import java.util.stream.Collectors;

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

    private BanInfo banInfo;

    private List<MessageDto> listMessage;

    private List<UserInRoomDto> listUserInRoom;

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setBanned(user.isBanned());
        userDto.setRole(user.getRole());
        userDto.setBanInfo(user.getBanInfo());
        userDto.setListMessage(user.getListMessage().stream().map(MessageDto::toDto).collect(Collectors.toList()));
        userDto.setListUserInRoom(user.getListUserInRoom().stream().map(UserInRoomDto::toDto).collect(Collectors.toList()));
        return userDto;
    }
}
