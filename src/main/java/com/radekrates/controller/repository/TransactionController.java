package com.radekrates.controller.repository;

import com.radekrates.domain.dto.transaction.TransactionDto;
import com.radekrates.domain.dto.transaction.TransactionToProcessDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.mapper.TransactionMapper;
import com.radekrates.service.TransactionServiceDb;
import com.radekrates.service.transactionfactory.TransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class TransactionController {
    private TransactionMapper transactionMapper;
    private TransactionServiceDb transactionServiceDb;
    private TransactionFactory transactionFactory;

    @Autowired
    public TransactionController(TransactionMapper transactionMapper, TransactionServiceDb transactionServiceDb,
        TransactionFactory transactionFactory) {
        this.transactionMapper = transactionMapper;
        this.transactionServiceDb = transactionServiceDb;
        this.transactionFactory = transactionFactory;
    }

    @PostMapping(value = "/transactions")
    public void saveTransactionFromFactory(@RequestBody TransactionToProcessDto transactionToProcessDto) {
        transactionServiceDb.saveTransaction(transactionFactory.createTransaction(transactionToProcessDto));
        transactionServiceDb.saveTransactionToUser(transactionToProcessDto);
    }

    @PostMapping(value = "/transactionsUser")
    public Set<TransactionDto> getTransactionsRelatedToUser(@RequestBody UserEmailDto userEmailDto) {
        return transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getTransactionsRelatedToUser(userEmailDto));
    }

    @GetMapping(value = "/transactions/{transactionId}")
    public TransactionDto getTransaction(@PathVariable Long transactionId) {
        return transactionMapper.mapToTransactionDto(transactionServiceDb.getTransactionById(transactionId));
    }

    @GetMapping(value = "/transactions")
    public Set<TransactionDto> getTransactions() {
        return transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getAllTransactions());
    }

    @DeleteMapping(value = "/transactions/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionServiceDb.deleteTransactionById(transactionId);
    }

    @DeleteMapping(value = "/transactions")
    public void deleteAllTransactions() {
        transactionServiceDb.deleteAllTransactions();
    }
}
