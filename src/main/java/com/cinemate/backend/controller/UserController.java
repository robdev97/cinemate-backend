package com.cinemate.backend.controller;

import com.cinemate.backend.domain.UserDto;
import com.cinemate.backend.mapper.UserMapper;
import com.cinemate.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(UserMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return UserMapper.toDto(
                userService.saveUser(UserMapper.fromDto(userDto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(
                    UserMapper.toDto(
                            userService.updateUser(id, UserMapper.fromDto(userDto))
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}