package com.radekrates.domain.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TransactionToProcessDto {
    private String userEmail;
    private String inputIbanNumber;
    private String outputIbanNumber;
    private String currencyPair;
    private BigDecimal inputValue;
}
