package com.cinemate.backend.controller;

import com.cinemate.backend.domain.ReviewDto;
import com.cinemate.backend.mapper.ReviewMapper;
import com.cinemate.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews().stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/movie/{movieId}")
    public List<ReviewDto> getReviewsByMovie(@PathVariable Long movieId) {
        return reviewService.getReviewsByMovieId(movieId).stream()
                .map(ReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ReviewMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        return ReviewMapper.toDto(
                reviewService.saveReview(ReviewMapper.fromDto(reviewDto))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        try {
            return ResponseEntity.ok(
                    ReviewMapper.toDto(
                            reviewService.updateReview(id, ReviewMapper.fromDto(reviewDto))
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}