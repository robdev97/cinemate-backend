package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.domain.WatchSessionDto;
import com.cinemate.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchSessionMapper {

    private final MovieRepository movieRepository;

    public WatchSessionDto toDto(WatchSession session) {
        String movieTitle = movieRepository.findById(session.getMovieId())
                .map(Movie::getTitle)
                .orElse("Unknown");

        return WatchSessionDto.builder()
                .id(session.getId())
                .movieId(session.getMovieId())
                .movieTitle(movieTitle)
                .scheduledDateTime(session.getScheduledDateTime())
                .location(session.getLocation())
                .build();
    }

    public WatchSession fromDto(WatchSessionDto dto) {
        return WatchSession.builder()
                .id(dto.getId())
                .movieId(dto.getMovieId())
                .scheduledDateTime(dto.getScheduledDateTime())
                .location(dto.getLocation())
                .build();
    }
}