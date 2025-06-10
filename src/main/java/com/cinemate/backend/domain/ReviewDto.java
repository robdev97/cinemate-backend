package com.cinemate.backend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private int rating;
    private String content;
    private LocalDateTime createdAt;
}