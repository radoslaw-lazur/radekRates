package com.radekrates.service.transactionfactory;

import com.radekrates.domain.dto.transaction.TransactionToProcessDto;
import com.radekrates.service.TransactionServiceDb;
import com.radekrates.service.datafixerio.calculation.CurrencyBase;
import com.radekrates.service.datafixerio.calculation.CurrrencyCalculator;
import com.radekrates.domain.Iban;
import com.radekrates.domain.Transaction;
import com.radekrates.repository.IbanRepository;
import com.radekrates.service.exceptions.iban.IbanNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

@Slf4j
@Getter
@Setter
@Service
public class TransactionFactory {
    @Autowired
    private IbanRepository ibanRepository;
    @Autowired
    private TransactionServiceDb transactionServiceDb;
    @Autowired
    private CurrrencyCalculator currrencyCalculator;
    private CurrencyBase currencyBase;
    private String uniqueStringChain;
    private BigDecimal ratio = new BigDecimal("1.1");
    private final static MathContext MATH_CONTEXT = new MathContext(4, RoundingMode.CEILING);

    public Transaction createTransaction(TransactionToProcessDto transactionProccesDto) {
        log.info("Creating transaction in progress... " + transactionProccesDto.getCurrencyPair());
        Iban inputIbanDb = ibanRepository.findByIbanNumber(transactionProccesDto.getInputIbanNumber())
                .orElseThrow(IbanNotFoundException::new);
        Iban outputIbanDb = ibanRepository.findByIbanNumber(transactionProccesDto.getOutputIbanNumber())
                .orElseThrow(IbanNotFoundException::new);

        currencyBase = currrencyCalculator.createLiveCurrencyBase(transactionProccesDto.getCurrencyPair().substring(0, 3));
        BigDecimal purchasedCurrency = recognizeSaleCurrency(transactionProccesDto.getCurrencyPair().substring(4, 7));
        BigDecimal soldCurrency = purchasedCurrency.multiply(ratio).round(MATH_CONTEXT);
        BigDecimal profit = soldCurrency.subtract(purchasedCurrency);
        uniqueStringChain = createUniqueStringChain();
        transactionServiceDb.setTemporaryUniqueStringChain(uniqueStringChain);

        return new Transaction(
                uniqueStringChain,
                inputIbanDb.getIbanNumber(),
                outputIbanDb.getIbanNumber(),
                transactionProccesDto.getCurrencyPair(),
                transactionProccesDto.getInputValue(),
                calculateOutputValue(transactionProccesDto.getInputValue(), soldCurrency),
                purchasedCurrency,
                soldCurrency,
                profit.multiply(transactionProccesDto.getInputValue()).round(MATH_CONTEXT),
                currencyBase.getDate()
        );
    }

    private String createUniqueStringChain() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 20;
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
        return inputValue.multiply(multiplier).round(MATH_CONTEXT);
    }
}
