package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
