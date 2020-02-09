package com.radekrates.domain.dto.datafixerio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesDto {
    @JsonProperty("EUR")
    private BigDecimal eur;
    @JsonProperty("PLN")
    private BigDecimal pln;
    @JsonProperty("GBP")
    private BigDecimal gbp;
    @JsonProperty("CHF")
    private BigDecimal chf;
    @JsonProperty("USD")
    private BigDecimal usd;
}
