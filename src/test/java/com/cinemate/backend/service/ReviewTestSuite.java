package com.cinemate.backend.service;


import com.cinemate.backend.domain.Review;
import com.cinemate.backend.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewTestSuite {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void shouldReturnAllReviews() {
        when(reviewRepository.findAll()).thenReturn(List.of(new Review(), new Review()));
        assertEquals(2, reviewService.getAllReviews().size());
    }

    @Test
    void shouldReturnReviewsByMovieId() {
        when(reviewRepository.findByMovieId(10L)).thenReturn(List.of(new Review()));
        assertEquals(1, reviewService.getReviewsByMovieId(10L).size());
    }

    @Test
    void shouldReturnReviewById() {
        Review review = new Review();
        when(reviewRepository.findById(5L)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.getReviewById(5L).get());
    }

    @Test
    void shouldSaveReview() {
        Review review = new Review();
        when(reviewRepository.save(review)).thenReturn(review);
        assertEquals(review, reviewService.saveReview(review));
    }

    @Test
    void shouldUpdateReview() {
        Review existing = Review.builder()
                .id(1L)
                .userId(1L)
                .movieId(10L)
                .content("Old")
                .rating(3)
                .createdAt(LocalDateTime.now())
                .build();

        Review updated = Review.builder()
                .id(1L)
                .userId(1L)
                .movieId(10L)
                .content("New")
                .rating(5)
                .createdAt(LocalDateTime.now())
                .build();

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(reviewRepository.save(any())).thenReturn(updated);

        Review result = reviewService.updateReview(1L, updated);

        assertEquals("New", result.getContent());
        assertEquals(5, result.getRating());
    }

    @Test
    void shouldDeleteReview() {
        reviewService.deleteReview(1L);
        verify(reviewRepository).deleteById(1L);
    }
}
