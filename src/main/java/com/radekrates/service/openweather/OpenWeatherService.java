package com.radekrates.service.openweather;

import com.radekrates.api.openweatherorg.client.OpenWeatherClient;
import com.radekrates.domain.dto.openweather.OpenWeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherService {
    private OpenWeatherClient openWeatherClient;

    @Autowired
    public OpenWeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    public OpenWeatherDto getWeatherCityData(String city, String region) {
        return openWeatherClient.getOpenWeatherData(city, region);
    }
}
