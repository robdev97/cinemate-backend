package com.cinemate.backend.controller;

import com.cinemate.backend.domain.ReviewDto;
import com.cinemate.backend.mapper.ReviewMapper;
import com.cinemate.backend.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void getAllReviews_shouldReturnList() throws Exception {
        // przykładowy ReviewDto, uzupełnij pola zgodnie z Twoim DTO
        var reviewDto = ReviewDto.builder().id(1L).content("Good movie").rating(5).build();

        when(reviewService.getAllReviews()).thenReturn(List.of(ReviewMapper.fromDto(reviewDto)));

        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Good movie"));
    }

    @Test
    void getReviewById_whenFound_shouldReturnReview() throws Exception {
        var reviewDto = ReviewDto.builder().id(1L).content("Nice").rating(4).build();

        when(reviewService.getReviewById(1L)).thenReturn(Optional.of(ReviewMapper.fromDto(reviewDto)));

        mockMvc.perform(get("/api/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Nice"));
    }

    @Test
    void getReviewById_whenNotFound_shouldReturn404() throws Exception {
        when(reviewService.getReviewById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/reviews/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createReview_shouldReturnCreatedReview() throws Exception {
        var reviewDto = ReviewDto.builder().content("Awesome").rating(5).build();
        var savedDto = ReviewDto.builder().id(1L).content("Awesome").rating(5).build();

        when(reviewService.saveReview(any())).thenReturn(ReviewMapper.fromDto(savedDto));

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"Awesome\",\"rating\":5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.content").value("Awesome"));
    }

    @Test
    void updateReview_whenFound_shouldReturnUpdated() throws Exception {
        var updatedDto = ReviewDto.builder().id(1L).content("Updated content").rating(4).build();

        when(reviewService.updateReview(Mockito.eq(1L), any())).thenReturn(ReviewMapper.fromDto(updatedDto));

        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"Updated content\",\"rating\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Updated content"));
    }

    @Test
    void updateReview_whenNotFound_shouldReturn404() throws Exception {
        when(reviewService.updateReview(Mockito.eq(1L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/reviews/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"Updated content\",\"rating\":4}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteReview_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isNoContent());
    }
}