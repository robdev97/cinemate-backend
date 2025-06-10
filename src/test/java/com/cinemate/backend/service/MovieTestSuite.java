package com.cinemate.backend.service;


import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieTestSuite {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldReturnAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(new Movie(), new Movie()));
        assertEquals(2, movieService.getAllMovies().size());
    }

    @Test
    void shouldReturnMovieById() {
        Movie movie = new Movie();
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        assertEquals(movie, movieService.getMovieById(1L).get());
    }

    @Test
    void shouldSaveMovie() {
        Movie movie = new Movie();
        when(movieRepository.save(movie)).thenReturn(movie);
        assertEquals(movie, movieService.saveMovie(movie));
    }

    @Test
    void shouldUpdateMovie() {
        Movie existing = new Movie(1L, "Old", "Dir", 2000);
        Movie updated = new Movie(1L, "New", "New Dir", 2020);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(movieRepository.save(any())).thenReturn(updated);
        Movie result = movieService.updateMovie(1L, updated);
        assertEquals("New", result.getTitle());
    }

    @Test
    void shouldDeleteMovie() {
        movieService.deleteMovie(1L);
        verify(movieRepository).deleteById(1L);
    }
}

