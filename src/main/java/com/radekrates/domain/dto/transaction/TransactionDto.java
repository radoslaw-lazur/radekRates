package com.radekrates.domain.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TransactionDto {
    private Long id;
    private String uniqueKeyChain;
    private String userEmail;
    private String inputIbanNumber;
    private String outputIbanNumber;
    private String pairOfCurrencies;
    private BigDecimal inputValue;
    private BigDecimal outputValue;
    private BigDecimal apiCurrencyPurchaseMultiplier;
    private BigDecimal currencySaleMultiplier;
    private BigDecimal profit;
    private LocalDate date;
}
