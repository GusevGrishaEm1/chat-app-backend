package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.DiscInfo;

@Repository
public interface DiscInfoRepo extends JpaRepository<DiscInfo, Long> {

}
