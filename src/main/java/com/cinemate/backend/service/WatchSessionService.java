package com.cinemate.backend.service;

import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.exception.WatchSessionNotFoundException;
import com.cinemate.backend.repository.WatchSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchSessionService {

    private final WatchSessionRepository watchSessionRepository;

    public List<WatchSession> getAllWatchSessions() {
        return watchSessionRepository.findAll();
    }

    public WatchSession getWatchSessionById(Long id) {
        return watchSessionRepository.findById(id)
                .orElseThrow(() -> new WatchSessionNotFoundException(id));
    }

    public WatchSession saveWatchSession(WatchSession watchSession) {
        return watchSessionRepository.save(watchSession);
    }

    public void deleteWatchSession(Long id) {
        if (!watchSessionRepository.existsById(id)) {
            throw new WatchSessionNotFoundException(id);
        }
        watchSessionRepository.deleteById(id);
    }
}