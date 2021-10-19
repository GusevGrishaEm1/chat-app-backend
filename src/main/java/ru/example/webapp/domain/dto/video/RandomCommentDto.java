package ru.example.webapp.domain.dto.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RandomCommentDto {
    private String login;
    private String text;
}
