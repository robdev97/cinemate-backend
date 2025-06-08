package com.cinemate.backend.controller;

import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.domain.dto.WatchSessionDto;
import com.cinemate.backend.mapper.WatchSessionMapper;
import com.cinemate.backend.service.WatchSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/watch-sessions")
@RequiredArgsConstructor
public class WatchSessionController {

    private final WatchSessionService watchSessionService;
    private final WatchSessionMapper watchSessionMapper;

    @GetMapping
    public ResponseEntity<List<WatchSessionDto>> getAllWatchSessions() {
        List<WatchSession> sessions = watchSessionService.getAllWatchSessions();
        List<WatchSessionDto> sessionDtos = sessions.stream()
                .map(watchSessionMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sessionDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchSessionDto> getWatchSessionById(@PathVariable Long id) {
        WatchSession session = watchSessionService.getWatchSessionById(id);
        return ResponseEntity.ok(watchSessionMapper.toDto(session));
    }

    @PostMapping
    public ResponseEntity<WatchSessionDto> createWatchSession(@RequestBody WatchSessionDto watchSessionDto) {
        WatchSession session = watchSessionMapper.toEntity(watchSessionDto);
        WatchSession savedSession = watchSessionService.saveWatchSession(session);
        return ResponseEntity.ok(watchSessionMapper.toDto(savedSession));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchSession(@PathVariable Long id) {
        watchSessionService.deleteWatchSession(id);
        return ResponseEntity.noContent().build();
    }
}