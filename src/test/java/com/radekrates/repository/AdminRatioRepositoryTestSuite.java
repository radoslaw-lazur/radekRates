package com.radekrates.repository;

import com.radekrates.domain.AdminRatio;
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
public class AdminRatioRepositoryTestSuite {
    @Autowired
    private AdminRatioRepository adminRatioRepository;
    private AdminRatio adminRatio;

    @Before
    public void createRatios() {
        adminRatio = new AdminRatio(
                1L,
                "123456789",
                LocalDate.of(2020, 3, 1),
                true,
                new BigDecimal("1"),
                new BigDecimal("2"),
                new BigDecimal("3"),
                new BigDecimal("4"),
                new BigDecimal("5"),
                new BigDecimal("6"),
                new BigDecimal("7"),
                new BigDecimal("8"),
                new BigDecimal("9"),
                new BigDecimal("10"),
                new BigDecimal("11"),
                new BigDecimal("12"),
                new BigDecimal("13"),
                new BigDecimal("14"),
                new BigDecimal("15"),
                new BigDecimal("16"),
                new BigDecimal("17"),
                new BigDecimal("18"),
                new BigDecimal("19"),
                new BigDecimal("20")
        );
    }

    @Test
    public void testSaveAdminRatio() {
        //Given
        adminRatioRepository.save(adminRatio);
        Long adminRatioId = adminRatio.getId();
        String adminRatioKey = adminRatio.getKey();
        //When
        Optional<AdminRatio> adminRatioDbKey = adminRatioRepository.findByKey(adminRatioKey);
        //Then
        assertTrue(adminRatioDbKey.isPresent());
    }

    @Test
    public void testAdminRatiosSize() {
        //Given
        adminRatioRepository.save(adminRatio);
        //When
        Set<AdminRatio> adminRatios = adminRatioRepository.findAll();
        //Then
        assertEquals(1, adminRatios.size());
    }

    @Test
    public void testFindAdminRatioByKey() {
        //Given
        adminRatioRepository.save(adminRatio);
        String key = adminRatio.getKey();
        //When
        Optional<AdminRatio> adminRatioDb = adminRatioRepository.findByKey(key);
        //Then
        assertTrue(adminRatioDb.isPresent());
        assertEquals(adminRatio.getKey(), adminRatioDb.get().getKey());
        assertEquals(adminRatio.getDate(), adminRatioDb.get().getDate());
        assertEquals(new BigDecimal("13.00"), adminRatioDb.get().getRatioCHF_EUR());
    }

    @After
    public void adminRatioCleanUp() {
        adminRatioRepository.deleteAll();
    }
}
