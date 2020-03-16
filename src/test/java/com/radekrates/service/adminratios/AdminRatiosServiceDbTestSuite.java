package com.radekrates.service.adminratios;

import com.radekrates.domain.AdminRatio;
import com.radekrates.service.AdminRatioServiceDb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRatiosServiceDbTestSuite {
    @Autowired
    private AdminRatioServiceDb adminRatioServiceDb;
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
    public void should() {

    }
}
