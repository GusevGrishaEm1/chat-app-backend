package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.UserInRoom;

public interface UserInRoomRepo extends JpaRepository<UserInRoom, Long> {

}
