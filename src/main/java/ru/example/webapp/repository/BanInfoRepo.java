package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.webapp.domain.BanInfo;

public interface BanInfoRepo extends JpaRepository<BanInfo, Long> {

}
