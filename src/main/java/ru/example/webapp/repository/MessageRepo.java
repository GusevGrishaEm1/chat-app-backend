package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

}
