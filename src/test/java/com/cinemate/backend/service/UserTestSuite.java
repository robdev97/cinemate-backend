package com.cinemate.backend.service;


import com.cinemate.backend.domain.User;
import com.cinemate.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTestSuite {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void shouldReturnUserById() {
        User user = new User();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserById(2L).get());
    }

    @Test
    void shouldSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.saveUser(user));
    }

    @Test
    void shouldUpdateUser() {
        User existing = new User(1L, "old", "old@mail.com");
        User updated = new User(1L, "new", "new@mail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any())).thenReturn(updated);
        User result = userService.updateUser(1L, updated);
        assertEquals("new", result.getUsername());
    }

    @Test
    void shouldDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
    }
}

