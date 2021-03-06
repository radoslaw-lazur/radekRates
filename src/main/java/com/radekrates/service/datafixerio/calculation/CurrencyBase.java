package com.radekrates.service.datafixerio.calculation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyBase {
    private String currencyBase;
    private LocalDate date;
    private BigDecimal eur;
    private BigDecimal pln;
    private BigDecimal gbp;
    private BigDecimal chf;
    private BigDecimal usd;
}
