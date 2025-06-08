package com.cinemate.backend.service;

import com.cinemate.backend.domain.User;
import com.cinemate.backend.exception.ResourceNotFoundException;
import com.cinemate.backend.exception.UserNotFoundException;
import com.cinemate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));

    }

    public User saveUser(User user) {
        validateUsernameAndEmailUniqueness(user);
        return userRepository.save(user);
    }
    private void validateUsernameAndEmailUniqueness(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    if (user.getId() == null || !existingUser.getId().equals(user.getId())) {
                        throw new IllegalArgumentException("Username is already taken");
                    }
                });

        userRepository.findAll().stream()
                .filter(existingUser -> existingUser.getEmail().equalsIgnoreCase(user.getEmail()))
                .findAny()
                .ifPresent(existingUser -> {
                    if (user.getId() == null || !existingUser.getId().equals(user.getId())) {
                        throw new IllegalArgumentException("Email is already taken");
                    }
                });
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        // Sprawdź, czy nowy username nie jest już używany przez innego użytkownika
        userRepository.findByUsername(updatedUser.getUsername())
                .ifPresent(userWithSameUsername -> {
                    if (!userWithSameUsername.getId().equals(id)) {
                        throw new IllegalArgumentException("Username is already taken");
                    }
                });

        // Sprawdź, czy nowy email nie jest już używany przez innego użytkownika
        userRepository.findAll().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(updatedUser.getEmail()))
                .findAny()
                .ifPresent(userWithSameEmail -> {
                    if (!userWithSameEmail.getId().equals(id)) {
                        throw new IllegalArgumentException("Email is already taken");
                    }
                });

        // Aktualizujemy tylko wybrane pola
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFullName(updatedUser.getFullName());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}