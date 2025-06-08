package com.cinemate.backend.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendDto {
    private Long id;
    private Long userId;
    private Long friendUserId;
    private String status;
}