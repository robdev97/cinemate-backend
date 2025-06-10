package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.domain.WatchSessionDto;
import com.cinemate.backend.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WatchSessionMapperTestSuite {

    private MovieRepository movieRepository;
    private WatchSessionMapper mapper;

    @BeforeEach
    void setUp() {
        movieRepository = Mockito.mock(MovieRepository.class);
        mapper = new WatchSessionMapper(movieRepository);
    }

    @Test
    void toDto_shouldMapAllFields_whenMovieExists() {
        WatchSession session = WatchSession.builder()
                .id(1L)
                .movieId(10L)
                .scheduledDateTime(LocalDateTime.of(2025, 6, 10, 20, 0))
                .location("Cinema City")
                .build();

        Movie movie = Movie.builder()
                .id(10L)
                .title("Inception")
                .build();

        when(movieRepository.findById(10L)).thenReturn(Optional.of(movie));

        WatchSessionDto dto = mapper.toDto(session);

        assertEquals(session.getId(), dto.getId());
        assertEquals(session.getMovieId(), dto.getMovieId());
        assertEquals("Inception", dto.getMovieTitle());
        assertEquals(session.getScheduledDateTime(), dto.getScheduledDateTime());
        assertEquals(session.getLocation(), dto.getLocation());

        verify(movieRepository).findById(10L);
    }

    @Test
    void toDto_shouldReturnUnknownTitle_whenMovieNotFound() {
        WatchSession session = WatchSession.builder()
                .id(2L)
                .movieId(20L)
                .scheduledDateTime(LocalDateTime.of(2025, 7, 15, 18, 30))
                .location("Cinema City")
                .build();

        when(movieRepository.findById(20L)).thenReturn(Optional.empty());

        WatchSessionDto dto = mapper.toDto(session);

        assertEquals("Unknown", dto.getMovieTitle());
    }

    @Test
    void fromDto_shouldMapAllFields() {
        WatchSessionDto dto = WatchSessionDto.builder()
                .id(3L)
                .movieId(30L)
                .movieTitle("Some Movie")  // powinno być ignorowane w fromDto
                .scheduledDateTime(LocalDateTime.of(2025, 8, 20, 21, 0))
                .location("IMAX")
                .build();

        WatchSession session = mapper.fromDto(dto);

        assertEquals(dto.getId(), session.getId());
        assertEquals(dto.getMovieId(), session.getMovieId());
        assertEquals(dto.getScheduledDateTime(), session.getScheduledDateTime());
        assertEquals(dto.getLocation(), session.getLocation());
    }
}
