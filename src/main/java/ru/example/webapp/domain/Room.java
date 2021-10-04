package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Please, fill the name of room")
    @Length(max = 64, message = "name is too long" )
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "type")
    private Type type;

    @Column(name = "privateRoom")
    private boolean privateRoom;

    @OneToMany(mappedBy = "room")
    private List<Message> listMessage = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<UserInRoom> listUserInRoom = new ArrayList<>();

}
