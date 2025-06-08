package com.cinemate.backend.repository;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
    List<Review> findAllByUser(User user);
}