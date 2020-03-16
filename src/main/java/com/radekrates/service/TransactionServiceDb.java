package com.radekrates.service;

import com.radekrates.domain.Mail;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
import com.radekrates.domain.dto.transaction.TransactionToProcessDto;
import com.radekrates.domain.dto.user.UserEmailDto;
import com.radekrates.repository.TransactionRepository;
import com.radekrates.repository.UserRepository;
import com.radekrates.service.exceptions.transaction.TransactionNotFoundException;
import com.radekrates.service.exceptions.transaction.TransactionToUserConflictException;
import com.radekrates.service.exceptions.user.UserNotFoundException;
import com.radekrates.service.mail.EmailService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Setter
@Service
public class TransactionServiceDb {
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private EmailService emailService;
    private String temporaryUniqueStringChain = "";
    private static final String SUBJECT_TRANSACTION = "New Transaction from Radoslaw's Rates Exchanges";

    @Autowired
    public TransactionServiceDb(TransactionRepository transactionRepository, UserRepository userRepository,
                                EmailService emailService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public Transaction saveTransaction(final Transaction transaction) {
        log.info("Transaction has been saved in database: " + transaction.getDate());
        return transactionRepository.save(transaction);
    }

    public void saveTransactionToUser(final TransactionToProcessDto transactionToProcessDto) {
        User user = userRepository.findByEmail(transactionToProcessDto.getUserEmail())
                .orElseThrow(UserNotFoundException::new);
        Transaction transaction = transactionRepository.findByUniqueKeyChain(temporaryUniqueStringChain)
                .orElseThrow(TransactionNotFoundException::new);
        if (transaction.getUniqueKeyChain().equals(temporaryUniqueStringChain) && user.getEmail()
                .equals(transactionToProcessDto.getUserEmail()) && user.isActive() && !user.isBlocked()) {
            user.getTransactions().add(transaction);
            transaction.setUser(user);
            userRepository.save(user);
            emailService.sendTransaction(new Mail(user.getEmail(), "Dear " + user.getUserFirstName() + ": "
                    + SUBJECT_TRANSACTION, ""), user, transaction);
            log.info("Transaction " + temporaryUniqueStringChain + " has been linked to " +
                    transactionToProcessDto.getUserEmail());
        } else {
            throw new TransactionToUserConflictException();
        }
    }

    public void deleteTransactionById(final Long transactionId) {
        if (transactionRepository.findById(transactionId).isPresent()) {
            Transaction transaction = transactionRepository.findById(transactionId)
                    .orElseThrow(TransactionNotFoundException::new);
            transaction.setUser(null);
            transactionRepository.save(transaction);
            transactionRepository.deleteById(transactionId);
            log.info("Transaction has been deleted from database - id: " + transactionId + "Email: " +
                    transaction.getUser().getEmail());
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

    public Set<Transaction> getTransactionsRelatedToUser(final UserEmailDto userEmailDto) {
        User user = userRepository.findByEmail(userEmailDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        log.info("Getting transactions related to: " + userEmailDto.getUserEmail());
        return user.getTransactions();
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }
}
