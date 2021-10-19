package ru.example.webapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageSenderDto {
        private String value;
        private long userId;
        private long roomId;
}
