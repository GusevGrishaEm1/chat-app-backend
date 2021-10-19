package ru.example.webapp.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "usr_in_room")
public class UserInRoom {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "owner")
    private boolean owner;

    @Column(name = "disconnected")
    private boolean disconnected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

}
