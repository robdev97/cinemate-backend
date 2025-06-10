package com.cinemate.backend.service;

import com.cinemate.backend.domain.Review;
import com.cinemate.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByMovieId(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setContent(reviewDetails.getContent());
                    review.setRating(reviewDetails.getRating());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}