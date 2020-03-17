package com.radekrates.domain.dto.adminratio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdminRatioDto {
    private Long id;
    private String key;
    private LocalDate date;
    private boolean active;
    private BigDecimal ratioEUR_PLN;
    private BigDecimal ratioEUR_GBP;
    private BigDecimal ratioEUR_CHF;
    private BigDecimal ratioEUR_USD;
    private BigDecimal ratioPLN_EUR;
    private BigDecimal ratioPLN_GBP;
    private BigDecimal ratioPLN_CHF;
    private BigDecimal ratioPLN_USD;
    private BigDecimal ratioGBP_EUR;
    private BigDecimal ratioGBP_PLN;
    private BigDecimal ratioGBP_CHF;
    private BigDecimal ratioGBP_USD;
    private BigDecimal ratioCHF_EUR;
    private BigDecimal ratioCHF_PLN;
    private BigDecimal ratioCHF_GBP;
    private BigDecimal ratioCHF_USD;
    private BigDecimal ratioUSD_EUR;
    private BigDecimal ratioUSD_PLN;
    private BigDecimal ratioUSD_GBP;
    private BigDecimal ratioUSD_CHF;
}
