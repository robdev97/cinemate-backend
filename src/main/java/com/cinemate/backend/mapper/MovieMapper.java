package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.MovieDto;

public class MovieMapper {

    public static MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .year(movie.getYear())
                .build();
    }

    public static Movie fromDto(MovieDto dto) {
        return Movie.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .director(dto.getDirector())
                .year(dto.getYear())
                .build();
    }
}
