package com.cinemate.backend.service;

import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.domain.WatchSessionDto;
import com.cinemate.backend.mapper.WatchSessionMapper;
import com.cinemate.backend.repository.WatchSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatchSessionService {

    private final WatchSessionRepository watchSessionRepository;

    public List<WatchSession> getAllSessions() {
        return watchSessionRepository.findAll();
    }

    public Optional<WatchSession> getSessionById(Long id) {
        return watchSessionRepository.findById(id);
    }

    public WatchSession saveSession(WatchSession session) {
        return watchSessionRepository.save(session);
    }

    public WatchSession updateSession(Long id, WatchSession updated) {
        return watchSessionRepository.findById(id)
                .map(existing -> {
                    existing.setMovieId(updated.getMovieId());
                    existing.setScheduledDateTime(updated.getScheduledDateTime());
                    existing.setLocation(updated.getLocation());
                    return watchSessionRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public void deleteSession(Long id) {
        watchSessionRepository.deleteById(id);
    }

    public List<WatchSession> getUpcomingSessions(LocalDateTime now) {
        return watchSessionRepository.findByScheduledDateTimeAfter(now);
    }
}