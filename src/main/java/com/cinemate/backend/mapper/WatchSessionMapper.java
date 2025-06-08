package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.WatchSession;
import com.cinemate.backend.domain.dto.MovieDto;
import com.cinemate.backend.domain.dto.WatchSessionDto;
import com.cinemate.backend.service.MovieService;
import com.cinemate.backend.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class WatchSessionMapper {

    private final MovieService movieService;
    private final UserService userService;
    private final MovieMapper movieMapper;

    public WatchSessionMapper(MovieService movieService, UserService userService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.userService = userService;
        this.movieMapper = movieMapper;
    }

    public WatchSessionDto toDto(WatchSession watchSession) {
        return WatchSessionDto.builder()
                .id(watchSession.getId())
                .movieTitle(watchSession.getMovie().getTitle())
                .hostUsername(watchSession.getHost().getUsername())
                .scheduledDateTime(watchSession.getScheduledDateTime())
                .status(watchSession.getStatus())
                .build();
    }

    public WatchSession toEntity(WatchSessionDto dto) {
        MovieDto movieDto = movieService.getMovieByTitle(dto.getMovieTitle());
        Movie movie = movieMapper.toEntity(movieDto);
        User host = userService.getUserByUsername(dto.getHostUsername());

        return WatchSession.builder()
                .id(dto.getId())
                .movie(movie)
                .host(host)
                .scheduledDateTime(dto.getScheduledDateTime())
                .status(dto.getStatus())
                .build();
    }
}
