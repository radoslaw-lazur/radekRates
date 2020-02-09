package com.radekrates.domain.dto.datafixerio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataFixerDto {
    @JsonProperty("timestamp")
    private String timeStamp;
    @JsonProperty("base")
    private String currencyBased;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("rates")
    private RatesDto ratesDto;
}
