package com.cinemate.backend.service;


import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.repository.WatchSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchSessionTestSuite {

    @Mock
    private WatchSessionRepository watchSessionRepository;

    @InjectMocks
    private WatchSessionService watchSessionService;

    @Test
    void shouldReturnAllSessions() {
        when(watchSessionRepository.findAll()).thenReturn(List.of(new WatchSession(), new WatchSession()));
        assertEquals(2, watchSessionService.getAllSessions().size());
    }

    @Test
    void shouldReturnSessionById() {
        WatchSession session = new WatchSession();
        when(watchSessionRepository.findById(3L)).thenReturn(Optional.of(session));
        assertEquals(session, watchSessionService.getSessionById(3L).get());
    }

    @Test
    void shouldSaveSession() {
        WatchSession session = new WatchSession();
        when(watchSessionRepository.save(session)).thenReturn(session);
        assertEquals(session, watchSessionService.saveSession(session));
    }

    @Test
    void shouldUpdateSession() {
        WatchSession existing = new WatchSession();
        existing.setId(1L);
        existing.setMovieId(101L);
        existing.setMovieTitle("Old Movie");
        existing.setScheduledDateTime(LocalDateTime.now());
        existing.setLocation("Old");

        WatchSession updated = new WatchSession();
        updated.setId(1L);
        updated.setMovieId(102L);
        updated.setMovieTitle("New Movie");
        updated.setScheduledDateTime(LocalDateTime.now().plusDays(1));
        updated.setLocation("New");

        when(watchSessionRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(watchSessionRepository.save(any())).thenReturn(updated);

        WatchSession result = watchSessionService.updateSession(1L, updated);

        assertEquals("New", result.getLocation());
        assertEquals(102L, result.getMovieId());
    }

    @Test
    void shouldDeleteSession() {
        watchSessionService.deleteSession(1L);
        verify(watchSessionRepository).deleteById(1L);
    }

    @Test
    void shouldReturnUpcomingSessions() {
        LocalDateTime now = LocalDateTime.now();
        when(watchSessionRepository.findByScheduledDateTimeAfter(now)).thenReturn(List.of(new WatchSession()));
        assertEquals(1, watchSessionService.getUpcomingSessions(now).size());
    }
}
