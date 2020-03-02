package com.radekrates.domain.dto.openweather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherDto {
    @JsonProperty("weather")
    private List<OpenWeatherWeatherDto> openWeatherWeatherDtos;
    @JsonProperty("main")
    private OpenWeatherMainDto openWeatherMainDto;
    @JsonProperty("wind")
    private OpenWeatherWindDto openWeatherWindDto;
    @JsonProperty("name")
    private String cityName;
}
