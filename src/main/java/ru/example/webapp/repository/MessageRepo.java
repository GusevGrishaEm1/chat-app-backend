package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
