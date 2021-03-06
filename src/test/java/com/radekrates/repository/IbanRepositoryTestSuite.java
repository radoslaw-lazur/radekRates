package com.radekrates.repository;

import com.radekrates.domain.Iban;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IbanRepositoryTestSuite {
    @Autowired
    private IbanRepository ibanRepository;
    private Iban iban1;
    private Iban iban2;

    @Before
    public void createIbans() {
        iban1 = new Iban(
                "bankName",
                "bankLocalisation",
                "PL",
                "PLN",
                "111111111111111111111111111111"
        );
        iban2 = new Iban(
                "bankName",
                "bankLocalisation",
                "DE",
                "EUR",
                "222222222222222222222222222222"
        );
    }

    @Test
    public void testSaveIbans() {
        //Given
        ibanRepository.save(iban1);
        Long iban1Id = iban1.getId();
        //When
        Optional<Iban> ibanDb = ibanRepository.findById(iban1Id);
        //Given
        assertTrue(ibanDb.isPresent());
    }

    @Test
    public void testIbanSize() {
        //Given
        ibanRepository.save(iban1);
        ibanRepository.save(iban2);
        //When
        Set<Iban> ibans = ibanRepository.findAll();
        long counts = ibanRepository.count();
        //Then
        assertEquals(2, ibans.size());
        assertEquals(2, (int) counts);
    }

    @Test
    public void testFindIbanById() {
        //Given
        ibanRepository.save(iban1);
        ibanRepository.save(iban2);
        Long iban1Id = iban1.getId();
        Long iban2Id = iban2.getId();
        //When
        Optional<Iban> iban1Db = ibanRepository.findById(iban1Id);
        Optional<Iban> iban2Db = ibanRepository.findById(iban2Id);
        //Then
        assertTrue(iban1Db.isPresent() && iban2Db.isPresent());
        assertEquals(iban1.getIbanNumber(), iban1Db.get().getIbanNumber());
        assertEquals(iban2.getIbanNumber(), iban2Db.get().getIbanNumber());
    }

    @Test
    public void testDeleteIbanById() {
        //Given
        ibanRepository.save(iban1);
        ibanRepository.save(iban2);
        Long iban1Id = iban1.getId();
        Long iban2Id = iban2.getId();
        //When
        ibanRepository.deleteById(iban1Id);
        Set<Iban> ibans = ibanRepository.findAll();
        Optional<Iban> iban1Db = ibanRepository.findById(iban1Id);
        Optional<Iban> iban2Db = ibanRepository.findById(iban2Id);
        //Then
        assertTrue(iban2Db.isPresent() && !iban1Db.isPresent());
        assertEquals(1, ibans.size());
    }

    @Test
    public void testFindIbanByIbanNumber() {
        //Given
        ibanRepository.save(iban1);
        ibanRepository.save(iban2);
        //When
        Optional<Iban> ibanFromDb = ibanRepository.findByIbanNumber("111111111111111111111111111111");
        //Then
        assertTrue(ibanFromDb.isPresent());
        assertEquals(iban1.getIbanNumber(), ibanFromDb.get().getIbanNumber());
    }

    @After
    public void ibansCleanUp() {
        ibanRepository.deleteAll();
    }
}
