package com.radekrates.mapper;

import com.radekrates.domain.Transaction;
import com.radekrates.domain.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {
    public Transaction mapToTransaction(final TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getInputIbanNumber(),
                transactionDto.getOutputIbanNumber(),
                transactionDto.getPairOfCurrencies(),
                transactionDto.getInputValue(),
                transactionDto.getOutputValue(),
                transactionDto.getApiCurrencyPurchaseMultiplier(),
                transactionDto.getCurrencySaleMultiplier(),
                transactionDto.getProfit(),
                transactionDto.getDate()
        );
    }
    public TransactionDto mapToTransactionDto(final Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getInputIbanNumber(),
                transaction.getOutputIbanNumber(),
                transaction.getPairOfCurrencies(),
                transaction.getInputValue(),
                transaction.getOutputValue(),
                transaction.getApiCurrencyPurchaseMultiplier(),
                transaction.getCurrencySaleMultiplier(),
                transaction.getProfit(),
                transaction.getDate()
        );
    }
    public Set<TransactionDto> mapToTransactionDtoSet(final Set<Transaction> transactions) {
        return transactions.stream()
                .map(t -> new TransactionDto(t.getId(), t.getInputIbanNumber(),t.getOutputIbanNumber(),
                        t.getPairOfCurrencies(), t.getInputValue(), t.getOutputValue(),
                        t.getApiCurrencyPurchaseMultiplier(), t.getCurrencySaleMultiplier(),
                        t.getProfit(), t.getDate()))
                .collect(Collectors.toSet());
    }
}
