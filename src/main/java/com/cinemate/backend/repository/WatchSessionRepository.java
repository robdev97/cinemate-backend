package com.cinemate.backend.repository;

import com.cinemate.backend.domain.WatchSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchSessionRepository extends JpaRepository<WatchSession, Long> {

}