package com.cinemate.backend.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private String director;
    private int year;
}