package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.ReviewDto;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .movieId(review.getMovieId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review fromDto(ReviewDto dto) {
        return Review.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .movieId(dto.getMovieId())
                .rating(dto.getRating())
                .content(dto.getContent())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}

