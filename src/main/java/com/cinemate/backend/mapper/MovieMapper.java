package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.dto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .rating(movie.getRating())
                .description(movie.getDescription())
                .build();
    }

    public Movie toEntity(MovieDto dto) {
        return Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .genre(dto.getGenre())
                .releaseYear(dto.getReleaseYear())
                .rating(dto.getRating())
                .description(dto.getDescription())
                .build();
    }
}
