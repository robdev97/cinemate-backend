package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.Movie;
import com.cinemate.backend.domain.Recommendation;
import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.dto.RecommendationDto;

public class RecommendationMapper {

    public static RecommendationDto mapToDto(Recommendation recommendation) {
        return RecommendationDto.builder()
                .id(recommendation.getId())
                .userId(recommendation.getUser().getId())
                .movieId(recommendation.getMovie().getId())
                .reason(recommendation.getReason())
                .status(recommendation.getStatus())
                .build();
    }

    public static Recommendation mapToEntity(RecommendationDto dto) {
        return Recommendation.builder()
                .id(dto.getId())
                .user(User.builder().id(dto.getUserId()).build())
                .movie(Movie.builder().id(dto.getMovieId()).build())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .build();
    }
}
