package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static User fromDto(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }
}
