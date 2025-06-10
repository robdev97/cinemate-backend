package com.cinemate.backend.controller;

import com.cinemate.backend.domain.MovieDto;
import com.cinemate.backend.mapper.MovieMapper;
import com.cinemate.backend.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void getAllMovies_shouldReturnList() throws Exception {
        var movie = MovieDto.builder().id(1L).title("Inception").build();
        when(movieService.getAllMovies()).thenReturn(List.of(MovieMapper.fromDto(movie)));

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }

    @Test
    void getMovieById_whenFound_shouldReturnMovie() throws Exception {
        var movie = MovieDto.builder().id(1L).title("Inception").build();
        when(movieService.getMovieById(1L)).thenReturn(Optional.of(MovieMapper.fromDto(movie)));

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void getMovieById_whenNotFound_shouldReturn404() throws Exception {
        when(movieService.getMovieById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createMovie_shouldReturnCreatedMovie() throws Exception {
        var movie = MovieDto.builder().id(null).title("New Movie").build();
        var savedMovie = MovieDto.builder().id(1L).title("New Movie").build();

        when(movieService.saveMovie(any())).thenReturn(MovieMapper.fromDto(savedMovie));

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Movie\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("New Movie"));
    }

    @Test
    void updateMovie_whenFound_shouldReturnUpdated() throws Exception {
        var updatedMovie = MovieDto.builder().id(1L).title("Updated Movie").build();

        when(movieService.updateMovie(Mockito.eq(1L), any())).thenReturn(MovieMapper.fromDto(updatedMovie));

        mockMvc.perform(put("/api/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Movie\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Movie"));
    }

    @Test
    void updateMovie_whenNotFound_shouldReturn404() throws Exception {
        when(movieService.updateMovie(Mockito.eq(1L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/movies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Movie\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMovie_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/movies/1"))
                .andExpect(status().isNoContent());
    }
}