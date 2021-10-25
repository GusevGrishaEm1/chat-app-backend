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
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "privateRoom")
    private boolean privateRoom;
    
}
