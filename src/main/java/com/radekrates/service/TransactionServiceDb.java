package com.radekrates.service;

import com.radekrates.domain.Transaction;
import com.radekrates.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class TransactionServiceDb {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(final Transaction transaction) {
        log.info("Transaction has been saved in database: " + transaction.getDate());
        return transactionRepository.save(transaction);
    }

    public void deleteTransactionById(final Long transactionId) {
        if (transactionRepository.findById(transactionId).isPresent()) {
            transactionRepository.deleteById(transactionId);
            log.info("Transaction has been deleted from database - id: " + transactionId);
        } else {
            log.info("Transaction is not present in database - id: " + transactionId);
        }
    }

    public Transaction getTransactionById(final Long transactionId) {
        log.info("Getting transaction by id in progress... " + transactionId);
        return transactionRepository.findById(transactionId).orElseThrow(TransactionNotFoundException::new);
    }

    public Set<Transaction> getAllTransactions() {
        log.info("Getting transactions in progress...");
        return transactionRepository.findAll();
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }

    public long countAll() {
        return transactionRepository.count();
    }
}
