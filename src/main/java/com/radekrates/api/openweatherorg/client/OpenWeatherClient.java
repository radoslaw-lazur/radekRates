package com.radekrates.api.openweatherorg.client;

import com.radekrates.api.openweatherorg.config.OpenWeatherConfig;
import com.radekrates.domain.dto.openweather.OpenWeatherDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Optional.ofNullable;

@Component
@Slf4j
public class OpenWeatherClient {
    private RestTemplate restTemplate;
    private OpenWeatherConfig openWeatherConfig;

    @Autowired
    public OpenWeatherClient(RestTemplate restTemplate, OpenWeatherConfig openWeatherConfig) {
        this.restTemplate = restTemplate;
        this.openWeatherConfig = openWeatherConfig;
    }

    public OpenWeatherDto getOpenWeatherData(String city, String region) {
        try {
            return ofNullable(restTemplate.getForObject(getOpenWeatherURL(city, region), OpenWeatherDto.class))
                    .orElseThrow(RuntimeException::new);
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new OpenWeatherDto();
        }
    }

    private URI getOpenWeatherURL(String city, String region) {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.getOpenWeatherApiEndpoint()
        + city + "," + region + openWeatherConfig.getOpenWeatherApiKey())
                .build().encode().toUri();
    }
}
