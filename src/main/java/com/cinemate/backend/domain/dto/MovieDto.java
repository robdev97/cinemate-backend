package com.cinemate.backend.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private Integer releaseYear;
    private Double rating;
    private String description;
}