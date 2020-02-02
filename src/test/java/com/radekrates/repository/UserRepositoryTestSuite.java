package com.radekrates.repository;

import com.radekrates.domain.Iban;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {
    @Autowired
    private UserRepository userRepository;
    private User user1;
    private User user2;

    @Before
    public void createUsers() {
        user1 = new User(
                "radoslaw.lazur@gmail.com",
                "password",
                "Radoslaw",
                "Lazur",
                30,
                "Poland",
                false,
                false
        );
        user2 = new User(
                "radoslaw.lazur.dev@gmail.com",
                "password",
                "Radoslaw",
                "Lazur",
                30,
                "Poland",
                false,
                false
        );
    }

    @Test
    public void testSaveUsers() {
        //Given
        userRepository.save(user1);
        Long userId = user1.getId();
        //When
        Optional<User> userDb = userRepository.findById(userId);
        //Then
        assertTrue(userDb.isPresent());
    }

    @Test
    public void testUsersSize() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        //When
        Set<User> users = userRepository.findAll();
        long counts = userRepository.count();
        //Then
        assertEquals(2, users.size());
        assertEquals(2, (int) counts);
    }

    @Test
    public void testFindUserById() {
        //Given
        userRepository.save(user1);
        userRepository.save(user2);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        //When
        Optional<User> user1Db = userRepository.findById(user1Id);
        Optional<User> user2Db = userRepository.findById(user2Id);
        //Then
        assertTrue(user1Db.isPresent() && user2Db.isPresent());
        assertEquals(user1.getEmail(), user1Db.get().getEmail());
        assertEquals(user2.isBlocked(), user2Db.get().isBlocked());
    }

    @Test
    public void testFindUserByEmail() {
        //Given
        userRepository.save(user1);
        Long user1Id = user1.getId();
        //When
        Optional<User> user1Db = userRepository.findByEmail("radoslaw.lazur@gmail.com");
        //Then
        assertTrue(user1Db.isPresent());
        assertEquals(user1.getEmail(), user1Db.get().getEmail());
    }

    @Test
    public void testSaveTransactionToUser() {
        //Given
        Transaction transaction1 = new Transaction(
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
        Transaction transaction2 = new Transaction(
                "inputIbanNumber",
                "outputIbanNumber",
                "PLN-EUR",
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                LocalDate.of(2019, 1, 1)
        );
        user1.getTransactions().add(transaction1);
        user2.getTransactions().add(transaction2);
        transaction1.setUser(user1);
        transaction2.setUser(user2);
        //When
        userRepository.save(user1);
        userRepository.save(user2);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        Optional<User> user1Db = userRepository.findById(user1Id);
        Optional<User> user2Db = userRepository.findById(user2Id);
        //Then
        assertTrue(user1Db.isPresent() && user2Db.isPresent());
        assertNotEquals(0, user2Db.get().getId().intValue());
        assertNotEquals(0, user2Db.get().getId().intValue());
        assertEquals(1, user1Db.get().getTransactions().size());
        assertEquals(1, user2Db.get().getTransactions().size());
    }

    @Test
    public void testSaveIbanToUser() {
        //Given
        Iban iban1 = new Iban(
                "bankName",
                "bankLocalisation",
                "111"
        );
        Iban iban2 = new Iban(
                "bankName",
                "bankLocalisation",
                "222"
        );
        user1.getIbans().add(iban1);
        user2.getIbans().add(iban2);
        iban1.setUser(user1);
        iban2.setUser(user2);
        //When
        userRepository.save(user1);
        userRepository.save(user2);
        Long user1Id = user1.getId();
        Long user2Id = user2.getId();
        Optional<User> user1Db = userRepository.findById(user1Id);
        Optional<User> user2Db = userRepository.findById(user2Id);
        //Then
        assertTrue(user1Db.isPresent() && user2Db.isPresent());
        assertNotEquals(0, user1Db.get().getId().intValue());
        assertNotEquals(0, user2Db.get().getId().intValue());
        assertEquals(1, user1Db.get().getIbans().size());
        assertEquals(1, user2Db.get().getIbans().size());
    }

    @After
    public void userCleanUp() {
        userRepository.deleteAll();
    }
}
