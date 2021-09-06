package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Type type;

    @Column(name = "is_private")
    private boolean isPrivate;

    @OneToMany(mappedBy = "room")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<UserInRoom> usersInRoom = new HashSet<>();

}
