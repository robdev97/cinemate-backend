package com.cinemate.backend.controller;

import com.cinemate.backend.domain.Review;
import com.cinemate.backend.domain.dto.ReviewDto;
import com.cinemate.backend.mapper.ReviewMapper;
import com.cinemate.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByMovie(@PathVariable Long movieId) {
        List<Review> reviews = reviewService.getReviewsForMovie(movieId);
        List<ReviewDto> dtos = reviews.stream()
                .map(ReviewMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(@PathVariable String username) {
        List<Review> reviews = reviewService.getReviewsByUser(username);
        List<ReviewDto> dtos = reviews.stream()
                .map(ReviewMapper::mapToDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{movieId}/add")
    public ResponseEntity<ReviewDto> addReview(
            @PathVariable Long movieId,
            @RequestParam String username,
            @RequestParam String content,
            @RequestParam int rating) {
        Review review = reviewService.addReview(movieId, username, content, rating);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReviewMapper.mapToDto(review));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}