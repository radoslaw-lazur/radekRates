package com.radekrates.mapper;

import com.radekrates.domain.Iban;
import com.radekrates.domain.dto.iban.IbanDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IbanMapperTestSuite {
    @Autowired
    private IbanMapper ibanMapper;
    private Iban iban;
    private IbanDto ibanDto;

    @Before
    public void init() {
        iban = new Iban(
                1L,
                "bankName",
                "bankLocalisation",
                "countryCode",
                "currencyCode",
                "ibanNumber"
        );
        ibanDto = new IbanDto(
                1L,
                "bankNameDto",
                "bankLocalisationDto",
                "countryCodeDto",
                "currencyCodeDto",
                "ibanNumberDto"
        );
    }

    @Test
    public void testMapToIbanDto() {
        //Given
        //When
        IbanDto mappedIbanDto = ibanMapper.mapToIbanDto(iban);
        //Then
        assertEquals(iban.getId(), mappedIbanDto.getId());
        assertEquals(iban.getBankName(), mappedIbanDto.getBankName());
        assertEquals(iban.getBankLocalisation(), mappedIbanDto.getBankLocalisation());
        assertEquals(iban.getCountryCode(), mappedIbanDto.getCountryCode());
        assertEquals(iban.getCurrencyCode(), mappedIbanDto.getCurrencyCode());
        assertEquals(iban.getIbanNumber(), mappedIbanDto.getIbanNumber());
    }

    @Test
    public void testMapToIban() {
        //Given
        //When
        Iban mappedIban = ibanMapper.mapToIban(ibanDto);
        //Then
        assertEquals(ibanDto.getId(), mappedIban.getId());
        assertEquals(ibanDto.getBankName(), mappedIban.getBankName());
        assertEquals(ibanDto.getBankLocalisation(), mappedIban.getBankLocalisation());
        assertEquals(ibanDto.getCountryCode(), mappedIban.getCountryCode());
        assertEquals(ibanDto.getCurrencyCode(), mappedIban.getCurrencyCode());
        assertEquals(ibanDto.getIbanNumber(), mappedIban.getIbanNumber());
    }

    @Test
    public void testMapToIbanDtoSet() {
        //Given
        Set<Iban> ibans = new HashSet<>();
        ibans.add(iban);
        //When
        Set<IbanDto> ibanDtos = ibanMapper.mapToIbanDtoSet(ibans);
        //Then
        assertEquals(ibans.size(), ibanDtos.size());
        assertEquals(ibans.iterator().next().getId(), ibanDtos.iterator().next().getId());
        assertEquals(ibans.iterator().next().getBankName(), ibanDtos.iterator().next().getBankName());
        assertEquals(ibans.iterator().next().getBankLocalisation(), ibanDtos.iterator().next().getBankLocalisation());
        assertEquals(ibans.iterator().next().getCountryCode(), ibanDtos.iterator().next().getCountryCode());
        assertEquals(ibans.iterator().next().getCurrencyCode(), ibanDtos.iterator().next().getCurrencyCode());
        assertEquals(ibans.iterator().next().getIbanNumber(), ibanDtos.iterator().next().getIbanNumber());
    }
}
