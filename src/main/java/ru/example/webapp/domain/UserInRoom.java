package ru.example.webapp.domain;

import lombok.Data;
import org.springframework.lang.Nullable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "userInRoom")
public class UserInRoom {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "owner")
    private boolean owner;

    @Column(name = "disconnected")
    private boolean disconnected;

    @Nullable
    @OneToOne(mappedBy = "userInRoom")
    private DiscInfo discInfo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
