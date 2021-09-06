package ru.example.webapp.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @NotBlank(message = "Please, fill the username")
    @Length(max = 64, message = "username is too long" )
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Please, fill the password")
    @Length(max = 64, message = "password is too long" )
    @Column(name = "password")
    private String password;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserInRoom> userInRooms = new HashSet<>();

    @Nullable
    @OneToOne(mappedBy = "user")
    private  BanInfo banInfo;

}
