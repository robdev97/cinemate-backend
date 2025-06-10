package com.cinemate.backend.facade;

import com.cinemate.backend.domain.enums.RecommendationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecommendationFacade {

    public List<String> filterRecommendationsByStatus(List<String> allRecommendations, RecommendationStatus status) {

        return allRecommendations.stream()
                .filter(r -> r.contains(status.name()))
                .collect(Collectors.toList());
    }
}