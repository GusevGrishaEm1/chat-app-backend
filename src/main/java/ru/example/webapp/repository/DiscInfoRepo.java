package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.DiscInfo;

public interface DiscInfoRepo extends JpaRepository<DiscInfo, Long> {

}
