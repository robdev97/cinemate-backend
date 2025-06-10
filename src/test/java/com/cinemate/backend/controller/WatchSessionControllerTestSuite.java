package com.cinemate.backend.controller;

import com.cinemate.backend.domain.WatchSessionDto;
import com.cinemate.backend.mapper.WatchSessionMapper;
import com.cinemate.backend.service.WatchSessionService;
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

@WebMvcTest(WatchSessionController.class)
class WatchSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WatchSessionService watchSessionService;

    @MockBean
    private WatchSessionMapper watchSessionMapper;



    @Test
    void getSessionById_whenNotFound_shouldReturn404() throws Exception {
        when(watchSessionService.getSessionById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/watch-sessions/1"))
                .andExpect(status().isNotFound());
    }


    @Test
    void updateSession_whenNotFound_shouldReturn404() throws Exception {
        when(watchSessionService.updateSession(Mockito.eq(1L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/api/watch-sessions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"location\":\"Room B\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteSession_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/watch-sessions/1"))
                .andExpect(status().isNoContent());
    }
}