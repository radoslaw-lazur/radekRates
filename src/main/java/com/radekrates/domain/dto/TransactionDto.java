package com.radekrates.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class TransactionDto {
    private Long id;
    private String from;
    private String to;
    private String pairIO;
    private BigDecimal input;
    private BigDecimal output;
    private LocalDate date;
    private boolean isAuthorized;
    private boolean isSuccessful;
}
