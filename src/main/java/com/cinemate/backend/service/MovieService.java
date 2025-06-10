package com.cinemate.backend.service;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setTitle(movieDetails.getTitle());
                    movie.setDirector(movieDetails.getDirector());
                    movie.setYear(movieDetails.getYear());
                    return movieRepository.save(movie);
                })
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + id));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}