package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

}
