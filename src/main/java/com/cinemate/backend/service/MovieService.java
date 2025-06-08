package com.cinemate.backend.service;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.dto.MovieDto;
import com.cinemate.backend.exception.ResourceNotFoundException;
import com.cinemate.backend.mapper.MovieMapper;
import com.cinemate.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(movieMapper::toDto)
                .toList();
    }

    public MovieDto getMovieById(Long id) {
        return movieMapper.toDto(movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found")));
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        Movie saved = movieRepository.save(movieMapper.toEntity(movieDto));
        return movieMapper.toDto(saved);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Movie with id " + id + " not found");
        }
        movieRepository.deleteById(id);
    }

    public MovieDto getMovieByTitle(String title) {
        return movieMapper.toDto(movieRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with title " + title + " not found")));
    }
}