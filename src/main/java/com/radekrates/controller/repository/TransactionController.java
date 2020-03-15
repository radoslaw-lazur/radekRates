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
@RequestMapping("/v1/transaction")
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

    @PostMapping(value = "saveTransactionFromFactory")
    public void saveTransactionFromFactory(@RequestBody TransactionToProcessDto transactionToProcessDto) {
        transactionServiceDb.saveTransaction(transactionFactory.createTransaction(transactionToProcessDto));
        transactionServiceDb.saveTransactionToUser(transactionToProcessDto);
    }

    @DeleteMapping(value = "deleteTransaction")
    public void deleteTransaction(@RequestParam Long transactionId) {
        transactionServiceDb.deleteTransactionById(transactionId);
    }

    @GetMapping(value = "getTransaction")
    public TransactionDto getTransaction(@RequestParam Long transactionId) {
        return transactionMapper.mapToTransactionDto(transactionServiceDb.getTransactionById(transactionId));
    }

    @GetMapping(value = "getTransactions")
    public Set<TransactionDto> getTransactions() {
        return transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getAllTransactions());
    }

    @PostMapping(value = "getTransactionsRelatedToUser")
    public Set<TransactionDto> getTransactionsRelatedToUser(@RequestBody UserEmailDto userEmailDto) {
        return transactionMapper.mapToTransactionDtoSet(transactionServiceDb.getTransactionsRelatedToUser(userEmailDto));
    }

    @DeleteMapping(value = "deleteAllTransactions")
    public void deleteAllTransactions() {
        transactionServiceDb.deleteAllTransactions();
    }
}
