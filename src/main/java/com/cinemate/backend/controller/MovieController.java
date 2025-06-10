package com.cinemate.backend.controller;

import com.cinemate.backend.domain.MovieDto;
import com.cinemate.backend.mapper.MovieMapper;
import com.cinemate.backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies().stream()
                .map(MovieMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(MovieMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody MovieDto movieDto) {
        return MovieMapper.toDto(
                movieService.saveMovie(MovieMapper.fromDto(movieDto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        try {
            return ResponseEntity.ok(
                    MovieMapper.toDto(
                            movieService.updateMovie(id, MovieMapper.fromDto(movieDto))
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}