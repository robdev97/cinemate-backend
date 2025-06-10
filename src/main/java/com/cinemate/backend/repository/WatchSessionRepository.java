package com.cinemate.backend.repository;

import com.cinemate.backend.domain.WatchSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WatchSessionRepository extends JpaRepository<WatchSession, Long> {
    List<WatchSession> findByScheduledDateTimeAfter(LocalDateTime dateTime);
}