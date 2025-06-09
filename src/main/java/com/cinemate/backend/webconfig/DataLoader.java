package com.cinemate.backend.webconfig;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final MovieRepository movieRepository;

    @PostConstruct
    public void loadData() {
        if (movieRepository.count() == 0) {
            movieRepository.save(Movie.builder()
                    .title("Inception")
                    .genre("Sci-Fi")
                    .releaseYear(2010)
                    .rating(8.8)
                    .description("A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea.")
                    .build());

            movieRepository.save(Movie.builder()
                    .title("The Matrix")
                    .genre("Action")
                    .releaseYear(1999)
                    .rating(8.7)
                    .description("A computer hacker learns about the true nature of his reality.")
                    .build());

            movieRepository.save(Movie.builder()
                    .title("Interstellar")
                    .genre("Sci-Fi")
                    .releaseYear(2014)
                    .rating(8.6)
                    .description("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.")
                    .build());
        }
    }
}