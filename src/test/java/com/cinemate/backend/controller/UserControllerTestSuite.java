package com.cinemate.backend.controller;

import com.cinemate.backend.domain.UserDto;
import com.cinemate.backend.mapper.UserMapper;
import com.cinemate.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers_shouldReturnList() throws Exception {
        var userDto = UserDto.builder().id(1L).username("Robert").build();

        when(userService.getAllUsers()).thenReturn(List.of(UserMapper.fromDto(userDto)));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Robert"));
    }

    @Test
    void getUserById_whenFound_shouldReturnUser() throws Exception {
        var userDto = UserDto.builder().id(1L).username("Robert").build();

        when(userService.getUserById(1L)).thenReturn(Optional.of(UserMapper.fromDto(userDto)));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Robert"));
    }

    @Test
    void getUserById_whenNotFound_shouldReturn404() throws Exception {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_shouldReturnCreatedUser() throws Exception {
        var userDto = UserDto.builder().username("NewUser").build();
        var savedUser = UserDto.builder().id(2L).username("NewUser").build();

        when(userService.saveUser(any())).thenReturn(UserMapper.fromDto(savedUser));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"NewUser\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.username").value("NewUser"));
    }

    @Test
    void updateUser_whenFound_shouldReturnUpdated() throws Exception {
        var updatedUser = UserDto.builder().id(1L).username("UpdatedName").build();

        when(userService.updateUser(Mockito.eq(1L), any())).thenReturn(UserMapper.fromDto(updatedUser));

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"UpdatedName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("UpdatedName"));
    }

    @Test
    void updateUser_whenNotFound_shouldReturn404() throws Exception {
        when(userService.updateUser(Mockito.eq(1L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"UpdatedName\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}