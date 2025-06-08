package com.cinemate.backend.service;

import com.cinemate.backend.domain.*;
import com.cinemate.backend.domain.enums.RecommendationStatus;
import com.cinemate.backend.exception.ResourceNotFoundException;
import com.cinemate.backend.repository.MovieRepository;
import com.cinemate.backend.repository.RecommendationRepository;
import com.cinemate.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<Recommendation> getRecommendationsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        return recommendationRepository.findAllByUser(user);
    }

    public Recommendation addRecommendation(String username, Long movieId, String reason) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + movieId));

        Recommendation recommendation = Recommendation.builder()
                .user(user)
                .movie(movie)
                .reason(reason)
                .status(RecommendationStatus.UNWATCHED)
                .build();

        return recommendationRepository.save(recommendation);
    }

    public Recommendation updateStatus(Long recommendationId, RecommendationStatus status) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found: " + recommendationId));

        recommendation.setStatus(status);
        return recommendationRepository.save(recommendation);
    }

    public void deleteRecommendation(Long recommendationId) {
        recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found: " + recommendationId));
        recommendationRepository.deleteById(recommendationId);
    }
}