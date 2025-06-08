package com.cinemate.backend.service;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.User;
import com.cinemate.backend.exception.ResourceNotFoundException;
import com.cinemate.backend.repository.MovieRepository;
import com.cinemate.backend.repository.ReviewRepository;
import com.cinemate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Review> getReviewsForMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        return reviewRepository.findAllByMovie(movie);
    }

    public List<Review> getReviewsByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return reviewRepository.findAllByUser(user);
    }

    public Review addReview(Long movieId, String username, String content, int rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        Review review = Review.builder()
                .movie(movie)
                .user(user)
                .content(content)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));
        reviewRepository.deleteById(reviewId);
    }
}