package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.UserInRoom;
import ru.example.webapp.domain.User;
import ru.example.webapp.domain.Room;

@Repository
public interface UserInRoomRepo extends JpaRepository<UserInRoom, Long> {
    public UserInRoom findById(long id);
    public UserInRoom findByUserAndRoom(User user, Room room);
}
