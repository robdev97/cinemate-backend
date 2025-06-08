package com.cinemate.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDto {
    private Long id;
    private Long movieId;
    private Long userId;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
}
