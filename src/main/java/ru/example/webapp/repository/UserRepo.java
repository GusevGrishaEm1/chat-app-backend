package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
