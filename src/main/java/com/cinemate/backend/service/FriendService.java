package com.cinemate.backend.service;

import com.cinemate.backend.domain.Friend;
import com.cinemate.backend.domain.User;
import com.cinemate.backend.domain.enums.FriendshipStatus;
import com.cinemate.backend.exception.ResourceNotFoundException;
import com.cinemate.backend.repository.FriendRepository;
import com.cinemate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public List<Friend> getFriendsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return friendRepository.findAllByUser(user);
    }

    public Friend addFriend(String username, String friendUsername) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        User friendUser = userRepository.findByUsername(friendUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Friend user not found: " + friendUsername));

        // Sprawdź, czy już istnieje taka relacja
        if (friendRepository.findByUserAndFriend(user, friendUser).isPresent()) {
            throw new IllegalStateException("Friendship already exists");
        }

        Friend friend = Friend.builder()
                .user(user)
                .friend(friendUser)
                .status(FriendshipStatus.ACCEPTED)
                .build();

        return friendRepository.save(friend);
    }

    public void removeFriend(Long friendId) {
        friendRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("Friend relation not found with id: " + friendId));
        friendRepository.deleteById(friendId);
    }
}