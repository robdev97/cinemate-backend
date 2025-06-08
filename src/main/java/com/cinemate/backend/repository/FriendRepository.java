package com.cinemate.backend.repository;

import com.cinemate.backend.domain.Friend;
import com.cinemate.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUser(User user);
    Optional<Friend> findByUserAndFriend(User user, User friend);
}