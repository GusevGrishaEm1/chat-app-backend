package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "disc_info")
public class DiscInfo {

    @Id
    private long id;

    @Range(min = 0, max = 525600, message = "The value must not go beyond the bounds(0-525600)")
    @Column(name= "minutes", nullable = false)
    private int minutes;

    @Column(name= "date_of_disc", nullable = false)
    private LocalDateTime dateOfDisc;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usr_in_room_id", nullable = false)
    private UserInRoom userInRoom;

}
