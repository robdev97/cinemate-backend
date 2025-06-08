package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto mapToDto(User user) {
        if (user == null) return null;

        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public static User mapToEntity(UserDto userDto) {
        if (userDto == null) return null;

        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .build();
    }

    public static User partialFromId(Long id) {
        return User.builder()
                .id(id)
                .build();
    }
}