package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.Room;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
