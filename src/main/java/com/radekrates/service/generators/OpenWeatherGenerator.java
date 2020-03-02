package com.radekrates.service.generators;

import com.radekrates.domain.dto.openweather.OpenWeatherDto;
import com.radekrates.service.openweather.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OpenWeatherGenerator {
    private OpenWeatherService openWeatherService;

    @Autowired
    public OpenWeatherGenerator(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    public String generateOpenWeatherData() {
        String warsaw = createCityData(openWeatherService.getWeatherCityData("Warsaw", "pl"));
        String london = createCityData(openWeatherService.getWeatherCityData("London", "uk"));
        String bern = createCityData(openWeatherService.getWeatherCityData("Bern", "ch"));
        String washington = createCityData(openWeatherService.getWeatherCityData("Washington", "us"));
        return warsaw + london + bern + washington;
    }

    private String createCityData(OpenWeatherDto openWeatherDto) {
        return openWeatherDto.getCityName() + " weather: " + "\n" +
                "Main: " + openWeatherDto.getOpenWeatherWeatherDtos().get(0).getMain() + "\n" +
                "Description: " + openWeatherDto.getOpenWeatherWeatherDtos().get(0).getDescription() + "\n" +
                "Temperature: " + kelvinToCelcius(openWeatherDto.getOpenWeatherMainDto().getTemp()) + " degree" + "\n" +
                "Feels like: " + kelvinToCelcius(openWeatherDto.getOpenWeatherMainDto().getFeels_like()) + " degree" + "\n" +
                "Pressure: " + openWeatherDto.getOpenWeatherMainDto().getPressure() + " hPa" + "\n" +
                "Wind: " + openWeatherDto.getOpenWeatherWindDto().getSpeed() + " km/h" + "\n" +
                "Humidity: " + openWeatherDto.getOpenWeatherMainDto().getHumidity() + "%" + "\n" + "\n";
    }

    private BigDecimal kelvinToCelcius (String tempKelvin) {
        BigDecimal kelvin = new BigDecimal(tempKelvin);
        return kelvin.subtract(new BigDecimal("273"));
    }
}
