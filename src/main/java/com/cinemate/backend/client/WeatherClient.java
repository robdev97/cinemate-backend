package com.cinemate.backend.client;

import org.springframework.stereotype.Component;

@Component
public class WeatherClient {

    public String getCurrentWeather(String location) {
        return "Sunny, 25°C";
    }
}