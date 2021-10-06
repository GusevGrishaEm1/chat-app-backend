package ru.example.webapp.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.UserInRoomDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDto {
    private MessageDtoRequest messageDtoRequest;
    private UserInRoomDto userInRoom;
}