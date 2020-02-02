package com.radekrates.repository;

import com.radekrates.domain.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionRepositoryTestSuite {
    @Autowired
    private TransactionRepository transactionRepository;
    private Transaction transaction1;
    private Transaction transaction2;

    @Before
    public void createTransactions() {
        transaction1 = new Transaction(
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 1, 1)
        );
        transaction2 = new Transaction(
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2020, 2, 1)
        );
    }

    @Test
    public void testSaveTransactions() {
        //Given
        transactionRepository.save(transaction1);
        Long transaction1Id = transaction1.getId();
        //When
        Optional<Transaction> transactionDb = transactionRepository.findById(transaction1Id);
        //Then
        assertTrue(transactionDb.isPresent());
    }

    @Test
    public void testTransactionsSize() {
        //Given
        transactionRepository.save(transaction1); transactionRepository.save(transaction2);
        //When
        Set<Transaction> transactions = transactionRepository.findAll();
        long counts = transactionRepository.count();
        //Then
        assertEquals(2, transactions.size());
        assertEquals(2, (int) counts);
    }

    @Test
    public void testFindTransactionById() {
        //Given
        transactionRepository.save(transaction1); transactionRepository.save(transaction2);
        Long transaction1Id = transaction1.getId();
        Long transaction2Id = transaction2.getId();
        //When
        Optional<Transaction> transaction1Db = transactionRepository.findById(transaction1Id);
        Optional<Transaction> transaction2Db = transactionRepository.findById(transaction2Id);
        //Then
        assertTrue(transaction1Db.isPresent() && transaction2Db.isPresent());
        assertEquals(transaction1.getPairOfCurrencies(), transaction1Db.get().getPairOfCurrencies());
        assertEquals(transaction2.getDate(), transaction2Db.get().getDate());
    }

    @Test
    public void testDeleteTransactionById() {
        //When
        transactionRepository.save(transaction1); transactionRepository.save(transaction2);
        Long transaction1Id = transaction1.getId();
        Long transaction2Id = transaction2.getId();
        //When
        transactionRepository.deleteById(transaction1Id);
        Set<Transaction> transactions = transactionRepository.findAll();
        Optional<Transaction> transaction1Db = transactionRepository.findById(transaction1Id);
        Optional<Transaction> transaction2Db = transactionRepository.findById(transaction2Id);
        //Then
        assertTrue(transaction2Db.isPresent() && !transaction1Db.isPresent());
        assertEquals(1, transactions.size());
    }

    @After
    public void transactionCleanUp() {
        transactionRepository.deleteAll();
    }
}
