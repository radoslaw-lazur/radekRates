package com.radekrates.service.transactionfactory;

import com.radekrates.service.datafixerio.calculation.CurrencyBase;
import com.radekrates.service.datafixerio.calculation.CurrrencyCalculator;
import com.radekrates.domain.Iban;
import com.radekrates.domain.Transaction;
import com.radekrates.repository.IbanRepository;
import com.radekrates.service.exceptions.iban.IbanNotFoundException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

@Slf4j
@Service
@Setter
public class TransactionFactory {
    @Autowired
    private IbanRepository ibanRepository;
    @Autowired
    private CurrrencyCalculator currrencyCalculator;
    private CurrencyBase currencyBase;
    private BigDecimal ratio = new BigDecimal("1.1");
    private final MathContext mathContext = new MathContext(4, RoundingMode.CEILING);

    public Transaction createTransaction(String inputIbanNumber, String outputIbanNumber,
                                         String pairOfCurrencies, BigDecimal inputValue) {
        log.info("Creating transaction in progress... " + pairOfCurrencies);
        Iban inputIbanDb = ibanRepository.findByIbanNumber(inputIbanNumber).orElseThrow(IbanNotFoundException::new);
        Iban outputIbanDb = ibanRepository.findByIbanNumber(outputIbanNumber).orElseThrow(IbanNotFoundException::new);

        currencyBase = currrencyCalculator.createLiveCurrencyBase(pairOfCurrencies.substring(0, 3));
        BigDecimal purchasedCurrency = recognizeSaleCurrency(pairOfCurrencies.substring(4, 7));
        BigDecimal soldCurrency = purchasedCurrency.multiply(ratio).round(mathContext);
        BigDecimal profit = soldCurrency.subtract(purchasedCurrency);

        return new Transaction(
                createUniqueStringChain(),
                inputIbanDb.getIbanNumber(),
                outputIbanDb.getIbanNumber(),
                pairOfCurrencies,
                inputValue,
                calculateOutputValue(inputValue, soldCurrency),
                purchasedCurrency,
                soldCurrency,
                profit.multiply(inputValue).round(mathContext),
                currencyBase.getDate()
        );
    }

    private String createUniqueStringChain() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 15;
        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private BigDecimal recognizeSaleCurrency(String saleCurrency) {
        switch (saleCurrency) {
            case "EUR":
                return currencyBase.getEur();
            case "PLN":
                return currencyBase.getPln();
            case "GBP":
                return currencyBase.getGbp();
            case "CHF":
                return currencyBase.getChf();
            case "USD":
                return currencyBase.getUsd();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    private BigDecimal calculateOutputValue(BigDecimal inputValue, BigDecimal multiplier) {
        return inputValue.multiply(multiplier).round(mathContext);
    }
}
