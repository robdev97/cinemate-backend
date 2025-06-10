package com.cinemate.backend.client;

import org.springframework.stereotype.Component;
import java.util.List;
import com.cinemate.backend.domain.Movie;

@Component
public class TmdbClient {

    public List<Movie> fetchPopularMovies() {
        return List.of(
                new Movie(null, "Inception", "asd", 2010),
                new Movie(null, "The Matrix", "asd2", 1999)
        );
    }
}