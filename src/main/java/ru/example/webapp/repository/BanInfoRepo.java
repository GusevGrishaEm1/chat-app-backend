package ru.example.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.webapp.domain.BanInfo;
import ru.example.webapp.domain.DiscInfo;

@Repository
public interface BanInfoRepo extends JpaRepository<BanInfo, Long> {
    public BanInfo findById(long id);
}
