package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.UserInRoomDtoRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePrivateRoomDto {
        private RoomDtoRequest roomDtoRequest;
        private UserDto userDtoFirst;
        private UserInRoomDtoRequest userInRoomDtoRequestFirst;
        private UserDto userDtoSecond;
        private UserInRoomDtoRequest userInRoomDtoRequestSecond;
}
