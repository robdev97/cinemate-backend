package com.cinemate.backend.mapper;

import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTestSuite {

    @Test
    void toDto_shouldMapAllFields() {
        User user = User.builder()
                .id(1L)
                .username("robert")
                .email("robert@example.com")
                .build();

        UserDto dto = UserMapper.toDto(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
        assertEquals(user.getEmail(), dto.getEmail());
    }

    @Test
    void fromDto_shouldMapAllFields() {
        UserDto dto = UserDto.builder()
                .id(2L)
                .username("anna")
                .email("anna@example.com")
                .build();

        User user = UserMapper.fromDto(dto);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getEmail(), user.getEmail());
    }
}

