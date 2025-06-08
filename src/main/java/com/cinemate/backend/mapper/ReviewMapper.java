package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.dto.ReviewDto;

public class ReviewMapper {

    public static ReviewDto mapToDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .movieId(review.getMovie().getId())
                .userId(review.getUser().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review mapToEntity(ReviewDto dto) {
        return Review.builder()
                .id(dto.getId())
                .movie(Movie.builder().id(dto.getMovieId()).build())
                .user(User.builder().id(dto.getUserId()).build())
                .content(dto.getContent())
                .rating(dto.getRating())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}