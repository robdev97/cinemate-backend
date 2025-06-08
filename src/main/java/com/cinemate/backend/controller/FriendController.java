package com.cinemate.backend.controller;

import com.cinemate.backend.domain.Friend;
import com.cinemate.backend.domain.dto.FriendDto;
import com.cinemate.backend.mapper.FriendMapper;
import com.cinemate.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/{username}")
    public ResponseEntity<List<FriendDto>> getFriends(@PathVariable String username) {
        List<Friend> friends = friendService.getFriendsForUser(username);
        List<FriendDto> dtos = friends.stream()
                .map(FriendMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{username}/add/{friendUsername}")
    public ResponseEntity<FriendDto> addFriend(@PathVariable String username, @PathVariable String friendUsername) {
        Friend friend = friendService.addFriend(username, friendUsername);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(FriendMapper.mapToDto(friend));
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long friendId) {
        friendService.removeFriend(friendId);
        return ResponseEntity.noContent().build();
    }
}