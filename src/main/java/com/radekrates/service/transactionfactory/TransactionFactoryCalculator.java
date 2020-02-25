package com.radekrates.service.transactionfactory;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class TransactionFactoryCalculator {
    private final static MathContext MATH_CONTEXT = new MathContext(4, RoundingMode.CEILING);

    public BigDecimal calculateOutputValue(BigDecimal inputValue, BigDecimal multiplier) {
        return inputValue.multiply(multiplier).round(MATH_CONTEXT);
    }

    public BigDecimal calculateProfit(BigDecimal unitProfit, BigDecimal multiplier) {
        return unitProfit.multiply(multiplier).round(MATH_CONTEXT);
    }
}
