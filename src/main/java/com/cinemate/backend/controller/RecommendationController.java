package com.cinemate.backend.controller;

import com.cinemate.backend.domain.Recommendation;
import com.cinemate.backend.domain.dto.RecommendationDto;
import com.cinemate.backend.domain.enums.RecommendationStatus;
import com.cinemate.backend.mapper.RecommendationMapper;
import com.cinemate.backend.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/user/{username}")
    public ResponseEntity<List<RecommendationDto>> getUserRecommendations(@PathVariable String username) {
        List<Recommendation> recommendations = recommendationService.getRecommendationsForUser(username);
        List<RecommendationDto> dtos = recommendations.stream()
                .map(RecommendationMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/add")
    public ResponseEntity<RecommendationDto> addRecommendation(
            @RequestParam String username,
            @RequestParam Long movieId,
            @RequestParam String reason) {
        Recommendation recommendation = recommendationService.addRecommendation(username, movieId, reason);
        return new ResponseEntity<>(RecommendationMapper.mapToDto(recommendation), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RecommendationDto> updateStatus(
            @PathVariable Long id,
            @RequestParam RecommendationStatus status) {
        Recommendation updated = recommendationService.updateStatus(id, status);
        return ResponseEntity.ok(RecommendationMapper.mapToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}