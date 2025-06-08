package com.cinemate.backend.domain;

import com.cinemate.backend.domain.enums.FriendshipStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Użytkownik, który wysłał zaproszenie/relację
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Użytkownik, który jest znajomym
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    // Możesz dodać pole statusu (np. PENDING, ACCEPTED) jeśli chcesz rozszerzyć później

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendshipStatus status;
}
