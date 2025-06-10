package com.cinemate.backend.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchSessionDto {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private LocalDateTime scheduledDateTime;
    private String location;
}