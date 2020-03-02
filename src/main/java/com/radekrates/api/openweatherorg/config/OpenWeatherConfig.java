package com.radekrates.api.openweatherorg.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OpenWeatherConfig {
    @Value("${openweathermap.endpoint.prod}")
    private String openWeatherApiEndpoint;
    @Value("${openweathermap.key}")
    private String openWeatherApiKey;
}
