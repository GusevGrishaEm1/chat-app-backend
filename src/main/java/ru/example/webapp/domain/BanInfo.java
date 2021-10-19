package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ban_info")
public class BanInfo {

    @Id
    private long id;

    @Range(min = 0, max = 525600, message = "The value must not go beyond the bounds(0-525600)")
    @Column(name = "minutes", nullable = false)
    private int minutes;

    @Column(name = "date_of_ban", nullable = false)
    private LocalDateTime dateOfBan;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;
}
