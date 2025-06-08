package com.cinemate.backend.repository;

import com.cinemate.backend.domain.Recommendation;
import com.cinemate.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findAllByUser(User user);
}