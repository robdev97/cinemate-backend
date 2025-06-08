package com.cinemate.backend.domain.dto;

import com.cinemate.backend.domain.enums.RecommendationStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private String reason;
    private RecommendationStatus status;
}