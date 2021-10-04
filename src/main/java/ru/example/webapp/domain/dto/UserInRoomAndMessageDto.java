package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.example.webapp.domain.dto.message.MessageDtoRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInRoomAndMessageDto {
    private UserInRoomDto userInRoom;
    private MessageDtoRequest message;
}
