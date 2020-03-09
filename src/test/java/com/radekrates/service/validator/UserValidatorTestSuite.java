package com.radekrates.service.validator;

import com.radekrates.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTestSuite {
    @Autowired
    private UserValidator userValidator;
    private User userShouldBeValidated;
    private User userShouldNotBeValidated;

    @Before
    public void init() {
        userShouldBeValidated = new User(
                "radoslaw.lazur@gmail.com",
                "javaMM!@3#$",
                "Radoslaw",
                "Lazur",
                30,
                "Poland",
                "abc",
                false,
                false
        );
        userShouldNotBeValidated = new User(
                "radoslaw.lazurgmail.com",
                "password",
                "Radoslaw",
                "Lazur",
                30,
                "Poland",
                "abc",
                false,
                false
        );
    }

    @Test
    public void testEmailValidation() {
        //Given
        //When
        boolean result1 = userValidator.validateUserEmail(userShouldBeValidated.getEmail());
        boolean result2 = userValidator.validateUserEmail(userShouldNotBeValidated.getEmail());
        //Then
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testPasswordValidation() {
        //Given
        //When
        boolean result1 = userValidator.validatePassword(userShouldBeValidated.getPassword());
        boolean result2 = userValidator.validatePassword(userShouldNotBeValidated.getPassword());
        //Then
        assertTrue(result1);
        assertFalse(result2);
    }
}
