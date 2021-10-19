package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Please, fill the message")
    @Length(max = 1024, message = "message is too long")
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date_of_send", nullable = false)
    private LocalDateTime dateOfSend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

}
