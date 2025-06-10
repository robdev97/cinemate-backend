package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.ReviewDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReviewMapperTestSuite {

    @Test
    void toDto_shouldMapAllFields() {
        LocalDateTime now = LocalDateTime.now();
        Review review = Review.builder()
                .id(1L)
                .userId(10L)
                .movieId(20L)
                .rating(8)
                .content("Great movie!")
                .createdAt(now)
                .build();

        ReviewDto dto = ReviewMapper.toDto(review);

        assertEquals(review.getId(), dto.getId());
        assertEquals(review.getUserId(), dto.getUserId());
        assertEquals(review.getMovieId(), dto.getMovieId());
        assertEquals(review.getRating(), dto.getRating());
        assertEquals(review.getContent(), dto.getContent());
        assertEquals(review.getCreatedAt(), dto.getCreatedAt());
    }

    @Test
    void fromDto_shouldMapAllFields() {
        LocalDateTime now = LocalDateTime.now();
        ReviewDto dto = ReviewDto.builder()
                .id(2L)
                .userId(11L)
                .movieId(21L)
                .rating(9)
                .content("Awesome!")
                .createdAt(now)
                .build();

        Review review = ReviewMapper.fromDto(dto);

        assertEquals(dto.getId(), review.getId());
        assertEquals(dto.getUserId(), review.getUserId());
        assertEquals(dto.getMovieId(), review.getMovieId());
        assertEquals(dto.getRating(), review.getRating());
        assertEquals(dto.getContent(), review.getContent());
        assertEquals(dto.getCreatedAt(), review.getCreatedAt());
    }
}