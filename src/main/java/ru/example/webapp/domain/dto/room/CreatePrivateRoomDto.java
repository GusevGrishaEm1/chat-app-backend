package ru.example.webapp.domain.dto.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.webapp.domain.dto.user.UserDto;
import ru.example.webapp.domain.dto.userInRoom.UserInRoomDtoRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePrivateRoomDto {
        private long firstUserId;
        private long secondUserId;
}
