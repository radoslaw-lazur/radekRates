package com.radekrates.mapper;

import com.radekrates.domain.Transaction;
import com.radekrates.domain.dto.transaction.TransactionDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionMapperTestSuite {
    @Autowired
    private TransactionMapper transactionMapper;
    private Transaction transaction;
    private TransactionDto transactionDto;

    @Before
    public void init() {
        transaction = new Transaction(
                1L,
                "uniqueKeyChain",
                "userEmail",
                "inputIbanNumber",
                "outputIbanNumber",
                "pairOfCurrencies",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 3, 1)
        );
        transactionDto = new TransactionDto(
                1L,
                "uniqueKeyChainDto",
                "userEmailDto",
                "inputIbanNumberDto",
                "outputIbanNumberDto",
                "pairOfCurrenciesDto",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 3, 1)
        );
    }

    @Test
    public void testMapToTransactionDto() {
        //Given
        //When
        TransactionDto mappedTransactionDto = transactionMapper.mapToTransactionDto(transaction);
        //Then
         assertEquals(transaction.getId(), mappedTransactionDto.getId());
         assertEquals(transaction.getUniqueKeyChain(), mappedTransactionDto.getUniqueKeyChain());
         assertEquals(transaction.getUserEmail(), mappedTransactionDto.getUserEmail());
         assertEquals(transaction.getInputIbanNumber(), mappedTransactionDto.getInputIbanNumber());
         assertEquals(transaction.getOutputIbanNumber(), mappedTransactionDto.getOutputIbanNumber());
         assertEquals(transaction.getPairOfCurrencies(), mappedTransactionDto.getPairOfCurrencies());
         assertEquals(transaction.getInputValue(), mappedTransactionDto.getInputValue());
         assertEquals(transaction.getOutputValue(), mappedTransactionDto.getOutputValue());
         assertEquals(transaction.getApiCurrencyPurchaseMultiplier(), mappedTransactionDto.getApiCurrencyPurchaseMultiplier());
         assertEquals(transaction.getApiCurrencyPurchaseMultiplier(), mappedTransactionDto.getApiCurrencyPurchaseMultiplier());
         assertEquals(transaction.getCurrencySaleMultiplier(), mappedTransactionDto.getCurrencySaleMultiplier());
         assertEquals(transaction.getProfit(), mappedTransactionDto.getProfit());
         assertEquals(transaction.getDate(), mappedTransactionDto.getDate());
    }

    @Test
    public void testMapToTransaction() {
        //Given
        //When
        Transaction mappedTransaction = transactionMapper.mapToTransaction(transactionDto);
        //Then
        assertEquals(transactionDto.getId(), mappedTransaction.getId());
        assertEquals(transactionDto.getUniqueKeyChain(), mappedTransaction.getUniqueKeyChain());
        assertEquals(transactionDto.getUserEmail(), mappedTransaction.getUserEmail());
        assertEquals(transactionDto.getInputIbanNumber(), mappedTransaction.getInputIbanNumber());
        assertEquals(transactionDto.getOutputIbanNumber(), mappedTransaction.getOutputIbanNumber());
        assertEquals(transactionDto.getPairOfCurrencies(), mappedTransaction.getPairOfCurrencies());
        assertEquals(transactionDto.getInputValue(), mappedTransaction.getInputValue());
        assertEquals(transactionDto.getOutputValue(), mappedTransaction.getOutputValue());
        assertEquals(transactionDto.getApiCurrencyPurchaseMultiplier(), mappedTransaction.getApiCurrencyPurchaseMultiplier());
        assertEquals(transactionDto.getApiCurrencyPurchaseMultiplier(), mappedTransaction.getApiCurrencyPurchaseMultiplier());
        assertEquals(transactionDto.getCurrencySaleMultiplier(), mappedTransaction.getCurrencySaleMultiplier());
        assertEquals(transactionDto.getProfit(), mappedTransaction.getProfit());
        assertEquals(transactionDto.getDate(), mappedTransaction.getDate());
    }

    @Test
    public void testMapToTransactionDtoSet() {
        //Given
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transaction);
        //When
        Set<TransactionDto> transactionDtos = transactionMapper.mapToTransactionDtoSet(transactions);
        //Then
        assertEquals(transactions.iterator().next().getId(), transactionDtos.iterator().next().getId());
        assertEquals(transactions.iterator().next().getUniqueKeyChain(), transactionDtos.iterator().next()
                .getUniqueKeyChain());
        assertEquals(transactions.iterator().next().getUserEmail(), transactionDtos.iterator().next()
                .getUserEmail());
        assertEquals(transactions.iterator().next().getInputIbanNumber(), transactionDtos.iterator().next()
                .getInputIbanNumber());
        assertEquals(transactions.iterator().next().getOutputIbanNumber(), transactionDtos.iterator().next()
                .getOutputIbanNumber());
        assertEquals(transactions.iterator().next().getPairOfCurrencies(), transactionDtos.iterator().next()
                .getPairOfCurrencies());
        assertEquals(transactions.iterator().next().getInputValue(), transactionDtos.iterator().next().getInputValue());
        assertEquals(transactions.iterator().next().getOutputValue(), transactionDtos.iterator().next().getOutputValue());
        assertEquals(transactions.iterator().next().getApiCurrencyPurchaseMultiplier(), transactionDtos.iterator().next()
                .getApiCurrencyPurchaseMultiplier());
        assertEquals(transactions.iterator().next().getApiCurrencyPurchaseMultiplier(), transactionDtos.iterator().next()
                .getApiCurrencyPurchaseMultiplier());
        assertEquals(transactions.iterator().next().getCurrencySaleMultiplier(), transactionDtos.iterator().next()
                .getCurrencySaleMultiplier());
        assertEquals(transactions.iterator().next().getProfit(), transactionDtos.iterator().next().getProfit());
        assertEquals(transactions.iterator().next().getDate(), transactionDtos.iterator().next().getDate());
    }
}
