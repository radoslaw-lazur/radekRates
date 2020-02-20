package com.radekrates.service;

import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.transaction.TransactionToProcessDto;
import com.radekrates.repository.TransactionRepository;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.transaction.TransactionNotFoundException;
import com.radekrates.service.exceptions.transaction.TransactionToUserConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Setter
@Service
public class TransactionServiceDb {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    private String temporaryUniqueStringChain = "";

    public Transaction saveTransaction(final Transaction transaction) {
        log.info("Transaction has been saved in database: " + transaction.getDate());
        return transactionRepository.save(transaction);
    }

    public void saveTransactionToUser(final TransactionToProcessDto transactionToProcessDto) {
        User user = userRepository.findByEmail(transactionToProcessDto.getUserEmail())
                .orElseThrow(UserNotFoundException::new);
        Transaction transaction = transactionRepository.findByUniqueStringChain(temporaryUniqueStringChain)
                .orElseThrow(TransactionNotFoundException::new);
        if (transaction.getUniqueStringChain().equals(temporaryUniqueStringChain)) {
            user.getTransactions().add(transaction);
            transaction.setUser(user);
            userRepository.save(user);
            log.info("Transaction " + temporaryUniqueStringChain + " has been linked to " +
                    transactionToProcessDto.getUserEmail());
        } else {
            throw new TransactionToUserConflictException();
        }
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
