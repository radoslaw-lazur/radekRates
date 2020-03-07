package com.radekrates.service.validator;

import com.radekrates.domain.Iban;
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
public class IbanValidatorTestSuite {
    @Autowired
    private IbanValidator ibanValidator;
    private Iban iban1;
    private Iban iban2;
    private Iban iban3;

    @Before
    public void init() {
        iban1 = new Iban(
                "bankName",
                "bankLocalisation",
                "DE",
                "GGG",
                "11111111111111111111"
        );
        iban2 = new Iban(
                "bankName",
                "bankLocalisation",
                "DE",
                "EUR",
                "PL111111111111111111"
        );
        iban3 = new Iban(
                "bankName",
                "bankLocalisation",
                "DE",
                "EUR",
                "111111111111111111DE"
        );
    }

    @Test
    public void testIbanValidator() {
        //Given
        //When
        boolean result1 = ibanValidator.validateIban(iban1);
        boolean result2 = ibanValidator.validateIban(iban2);
        boolean result3 = ibanValidator.validateIban(iban3);
        //Then
        assertFalse(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
}
