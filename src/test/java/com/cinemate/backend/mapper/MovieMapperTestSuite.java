package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.MovieDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTestSuite {

    @Test
    void toDto_shouldMapAllFields() {
        Movie movie = Movie.builder()
                .id(1L)
                .title("Inception")
                .director("Christopher Nolan")
                .year(2010)
                .build();

        MovieDto dto = MovieMapper.toDto(movie);

        assertEquals(movie.getId(), dto.getId());
        assertEquals(movie.getTitle(), dto.getTitle());
        assertEquals(movie.getDirector(), dto.getDirector());
        assertEquals(movie.getYear(), dto.getYear());
    }

    @Test
    void fromDto_shouldMapAllFields() {
        MovieDto dto = MovieDto.builder()
                .id(2L)
                .title("Interstellar")
                .director("Christopher Nolan")
                .year(2014)
                .build();

        Movie movie = MovieMapper.fromDto(dto);

        assertEquals(dto.getId(), movie.getId());
        assertEquals(dto.getTitle(), movie.getTitle());
        assertEquals(dto.getDirector(), movie.getDirector());
        assertEquals(dto.getYear(), movie.getYear());
    }
}