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
                transactionDto.getFrom(),
                transactionDto.getTo(),
                transactionDto.getPairIO(),
                transactionDto.getInput(),
                transactionDto.getOutput(),
                transactionDto.getDate(),
                transactionDto.isAuthorized(),
                transactionDto.isSuccessful()
        );
    }
    public TransactionDto mapToTransactionDto(final Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getFrom(),
                transaction.getTo(),
                transaction.getPairIO(),
                transaction.getInput(),
                transaction.getOutput(),
                transaction.getDate(),
                transaction.isAuthorized(),
                transaction.isSuccessful()
        );
    }
    public Set<TransactionDto> mapToTransactionDtoSet(final Set<Transaction> transactions) {
        return transactions.stream()
                .map(t -> new TransactionDto(t.getId(), t.getFrom(),t.getTo(), t.getPairIO(), t.getInput(),
                        t.getOutput(), t.getDate(), t.isAuthorized(), t.isSuccessful()))
                .collect(Collectors.toSet());
    }
}
