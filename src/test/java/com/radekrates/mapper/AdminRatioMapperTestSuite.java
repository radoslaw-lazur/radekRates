package com.radekrates.mapper;

import com.radekrates.domain.AdminRatio;
import com.radekrates.domain.dto.adminratio.AdminRatioDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminRatioMapperTestSuite {
    @Autowired
    private AdminRatioMapper adminRatioMapper;
    private AdminRatio adminRatio;
    private AdminRatioDto adminRatioDto;

    @Before
    public void init() {
        adminRatio = new AdminRatio(
                1L,
                "key",
                LocalDate.of(2020, 3, 1),
                true,
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1")
        );
        adminRatioDto = new AdminRatioDto(
                1L,
                "keyDto",
                LocalDate.of(2020, 3, 1),
                true,
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1"),
                new BigDecimal("1")
        );
    }

    @Test
    public void testMapToAdminRatio() {
        //Given
        //When
        AdminRatio mappedAdminRatio = adminRatioMapper.mapToAdminRatio(adminRatioDto);
        //Then
        assertEquals(adminRatioDto.getId(), mappedAdminRatio.getId());
        assertEquals(adminRatioDto.getKey(), mappedAdminRatio.getKey());
        assertEquals(adminRatioDto.getDate(), mappedAdminRatio.getDate());
        assertTrue(adminRatioDto.isActive() && mappedAdminRatio.isActive());
        assertEquals(adminRatioDto.getRatioEUR_PLN(), mappedAdminRatio.getRatioEUR_PLN());
        assertEquals(adminRatioDto.getRatioEUR_GBP(), mappedAdminRatio.getRatioEUR_GBP());
        assertEquals(adminRatioDto.getRatioEUR_CHF(), mappedAdminRatio.getRatioEUR_CHF());
        assertEquals(adminRatioDto.getRatioEUR_USD(), mappedAdminRatio.getRatioEUR_USD());
        assertEquals(adminRatioDto.getRatioPLN_EUR(), mappedAdminRatio.getRatioPLN_EUR());
        assertEquals(adminRatioDto.getRatioPLN_GBP(), mappedAdminRatio.getRatioPLN_GBP());
        assertEquals(adminRatioDto.getRatioPLN_CHF(), mappedAdminRatio.getRatioPLN_CHF());
        assertEquals(adminRatioDto.getRatioPLN_USD(), mappedAdminRatio.getRatioPLN_USD());
        assertEquals(adminRatioDto.getRatioGBP_EUR(), mappedAdminRatio.getRatioGBP_EUR());
        assertEquals(adminRatioDto.getRatioGBP_PLN(), mappedAdminRatio.getRatioGBP_PLN());
        assertEquals(adminRatioDto.getRatioGBP_CHF(), mappedAdminRatio.getRatioGBP_CHF());
        assertEquals(adminRatioDto.getRatioGBP_USD(), mappedAdminRatio.getRatioGBP_USD());
        assertEquals(adminRatioDto.getRatioCHF_EUR(), mappedAdminRatio.getRatioCHF_EUR());
        assertEquals(adminRatioDto.getRatioCHF_PLN(), mappedAdminRatio.getRatioCHF_PLN());
        assertEquals(adminRatioDto.getRatioCHF_GBP(), mappedAdminRatio.getRatioCHF_GBP());
        assertEquals(adminRatioDto.getRatioCHF_USD(), mappedAdminRatio.getRatioCHF_USD());
        assertEquals(adminRatioDto.getRatioUSD_EUR(), mappedAdminRatio.getRatioUSD_EUR());
        assertEquals(adminRatioDto.getRatioUSD_PLN(), mappedAdminRatio.getRatioUSD_PLN());
        assertEquals(adminRatioDto.getRatioUSD_GBP(), mappedAdminRatio.getRatioUSD_GBP());
        assertEquals(adminRatioDto.getRatioUSD_CHF(), mappedAdminRatio.getRatioUSD_CHF());
    }

    @Test
    public void testMapToAdminRatioDtoSet() {
        //Given
        Set<AdminRatio> adminRatios = new HashSet<>();
        adminRatios.add(adminRatio);
        //When
        Set<AdminRatioDto> adminRatioDtos = adminRatioMapper.mapToAdminRatioDtoSet(adminRatios);
        //Then
        assertEquals(adminRatios.iterator().next().getId(), adminRatioDtos.iterator().next().getId());
        assertEquals(adminRatios.iterator().next().getKey(), adminRatioDtos.iterator().next().getKey());
        assertEquals(adminRatios.iterator().next().getDate(), adminRatioDtos.iterator().next().getDate());
        assertTrue(adminRatios.iterator().next().isActive() && adminRatioDtos.iterator().next().isActive());
        assertEquals(adminRatios.iterator().next().getRatioEUR_PLN(), adminRatioDtos.iterator().next().getRatioEUR_PLN());
        assertEquals(adminRatios.iterator().next().getRatioEUR_GBP(), adminRatioDtos.iterator().next().getRatioEUR_GBP());
        assertEquals(adminRatios.iterator().next().getRatioEUR_CHF(), adminRatioDtos.iterator().next().getRatioEUR_CHF());
        assertEquals(adminRatios.iterator().next().getRatioEUR_USD(), adminRatioDtos.iterator().next().getRatioEUR_USD());
        assertEquals(adminRatios.iterator().next().getRatioPLN_EUR(), adminRatioDtos.iterator().next().getRatioPLN_EUR());
        assertEquals(adminRatios.iterator().next().getRatioPLN_GBP(), adminRatioDtos.iterator().next().getRatioPLN_GBP());
        assertEquals(adminRatios.iterator().next().getRatioPLN_CHF(), adminRatioDtos.iterator().next().getRatioPLN_CHF());
        assertEquals(adminRatios.iterator().next().getRatioPLN_USD(), adminRatioDtos.iterator().next().getRatioPLN_USD());
        assertEquals(adminRatios.iterator().next().getRatioGBP_EUR(), adminRatioDtos.iterator().next().getRatioGBP_EUR());
        assertEquals(adminRatios.iterator().next().getRatioGBP_PLN(), adminRatioDtos.iterator().next().getRatioGBP_PLN());
        assertEquals(adminRatios.iterator().next().getRatioGBP_CHF(), adminRatioDtos.iterator().next().getRatioGBP_CHF());
        assertEquals(adminRatios.iterator().next().getRatioGBP_USD(), adminRatioDtos.iterator().next().getRatioGBP_USD());
        assertEquals(adminRatios.iterator().next().getRatioCHF_EUR(), adminRatioDtos.iterator().next().getRatioCHF_EUR());
        assertEquals(adminRatios.iterator().next().getRatioCHF_PLN(), adminRatioDtos.iterator().next().getRatioCHF_PLN());
        assertEquals(adminRatios.iterator().next().getRatioCHF_GBP(), adminRatioDtos.iterator().next().getRatioCHF_GBP());
        assertEquals(adminRatios.iterator().next().getRatioCHF_USD(), adminRatioDtos.iterator().next().getRatioCHF_USD());
        assertEquals(adminRatios.iterator().next().getRatioUSD_EUR(), adminRatioDtos.iterator().next().getRatioUSD_EUR());
        assertEquals(adminRatios.iterator().next().getRatioUSD_PLN(), adminRatioDtos.iterator().next().getRatioUSD_PLN());
        assertEquals(adminRatios.iterator().next().getRatioUSD_GBP(), adminRatioDtos.iterator().next().getRatioUSD_GBP());
        assertEquals(adminRatios.iterator().next().getRatioUSD_CHF(), adminRatioDtos.iterator().next().getRatioUSD_CHF());
    }
}
