package com.cinemate.backend.controller;

import com.cinemate.backend.domain.WatchSessionDto;
import com.cinemate.backend.mapper.WatchSessionMapper;
import com.cinemate.backend.service.WatchSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/watch-sessions")
@RequiredArgsConstructor
public class WatchSessionController {

    private final WatchSessionService watchSessionService;
    private final WatchSessionMapper watchSessionMapper;

    @GetMapping
    public List<WatchSessionDto> getAllSessions() {
        return watchSessionService.getAllSessions().stream()
                .map(watchSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchSessionDto> getSessionById(@PathVariable Long id) {
        return watchSessionService.getSessionById(id)
                .map(watchSessionMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WatchSessionDto createSession(@RequestBody WatchSessionDto dto) {
        return watchSessionMapper.toDto(
                watchSessionService.saveSession(
                        watchSessionMapper.fromDto(dto)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchSessionDto> updateSession(@PathVariable Long id, @RequestBody WatchSessionDto dto) {
        try {
            return ResponseEntity.ok(
                    watchSessionMapper.toDto(
                            watchSessionService.updateSession(id, watchSessionMapper.fromDto(dto))
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        watchSessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/upcoming")
    public List<WatchSessionDto> getUpcomingSessions() {
        return watchSessionService.getUpcomingSessions(LocalDateTime.now()).stream()
                .map(watchSessionMapper::toDto)
                .collect(Collectors.toList());
    }
}