package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.DiscInfo;
import ru.example.webapp.domain.Room;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.UserInRoom;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInRoomDto {

    private long id;

    private boolean owner;

    private boolean disconnected;

    private DiscInfo discInfo;

    private Room room;

    private User user;

    public static UserInRoomDto toDto(UserInRoom userInRoom) {
        UserInRoomDto userInRoomDto = new UserInRoomDto();
        userInRoomDto.setId(userInRoom.getId());
        userInRoomDto.setOwner(userInRoom.isOwner());
        userInRoomDto.setDiscInfo(userInRoom.getDiscInfo());
        userInRoomDto.setRoom(userInRoom.getRoom());
        userInRoomDto.setUser(userInRoom.getUser());
        return userInRoomDto;
    }
}
