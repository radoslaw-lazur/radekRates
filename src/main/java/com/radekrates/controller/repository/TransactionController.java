package com.radekrates.controller.repository;

import com.radekrates.domain.dto.TransactionDto;
import com.radekrates.mapper.TransactionMapper;
import com.radekrates.service.TransactionServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionServiceDb transactionServiceDb;

    @PostMapping(value = "saveTransaction")
    public void  saveTransaction(@RequestBody TransactionDto transactionDto) {
        transactionServiceDb.saveTransaction(transactionMapper.mapToTransaction(transactionDto));
    }
    @PutMapping(value = "updateTransaction")
    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionMapper.mapToTransactionDto(transactionServiceDb.saveTransaction(transactionMapper.
                mapToTransaction(transactionDto)));
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
    @DeleteMapping(value = "deleteAllTransactions")
    public void deleteAllTransactions() {
        transactionServiceDb.deleteAllTransactions();
    }
}
