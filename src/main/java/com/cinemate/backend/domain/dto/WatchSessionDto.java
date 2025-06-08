package com.cinemate.backend.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchSessionDto {
    private Long id;
    private String movieTitle;
    private String hostUsername;
    private LocalDateTime scheduledDateTime;
    private String status;
}