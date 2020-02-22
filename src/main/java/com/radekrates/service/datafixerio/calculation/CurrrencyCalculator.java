package com.radekrates.service.datafixerio.calculation;

import com.radekrates.api.datafixerio.client.DataFixerClient;
import com.radekrates.domain.dto.datafixerio.DataFixerDto;
import com.radekrates.domain.dto.datafixerio.RatesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CurrrencyCalculator {
    private DataFixerClient dataFixerClient;
    private final static MathContext MATH_CONTEXT = new MathContext(4, RoundingMode.CEILING);
    private final static int SCALE = 4;

    @Autowired
    public CurrrencyCalculator(DataFixerClient dataFixerClient) {
        this.dataFixerClient = dataFixerClient;
    }

    public CurrencyBase createLiveCurrencyBase(String currency) {
        DataFixerDto dataFixerDto = getDataFixerData();
        RatesDto ratesDto = getDataFixerData().getRatesDto();
        switch (currency) {
            case "EUR":
                return new CurrencyBase(
                        dataFixerDto.getCurrencyBased(),
                        dataFixerDto.getDate(),
                        ratesDto.getEur(),
                        ratesDto.getPln().round(MATH_CONTEXT),
                        ratesDto.getGbp().round(MATH_CONTEXT),
                        ratesDto.getChf().round(MATH_CONTEXT),
                        ratesDto.getUsd().round(MATH_CONTEXT)
                );
            case "PLN":
                BigDecimal eurToPln = BigDecimal.ONE.divide(ratesDto.getPln(), SCALE, RoundingMode.CEILING);
                return new CurrencyBase(
                        "PLN",
                        dataFixerDto.getDate(),
                        eurToPln,
                        BigDecimal.ONE,
                        eurToPln.multiply(ratesDto.getGbp()).round(MATH_CONTEXT),
                        eurToPln.multiply(ratesDto.getChf()).round(MATH_CONTEXT),
                        eurToPln.multiply(ratesDto.getUsd()).round(MATH_CONTEXT)
                );
            case "GBP":
                BigDecimal eurToGbp = BigDecimal.ONE.divide(ratesDto.getGbp(), SCALE, RoundingMode.CEILING);
                return new CurrencyBase(
                        "GBP",
                        dataFixerDto.getDate(),
                        eurToGbp,
                        eurToGbp.multiply(ratesDto.getPln()).round(MATH_CONTEXT),
                        BigDecimal.ONE,
                        eurToGbp.multiply(ratesDto.getChf()).round(MATH_CONTEXT),
                        eurToGbp.multiply(ratesDto.getUsd()).round(MATH_CONTEXT)
                );
            case "CHF":
                BigDecimal eurToChf = BigDecimal.ONE.divide(ratesDto.getChf(), SCALE, RoundingMode.CEILING);
                return new CurrencyBase(
                        "CHF",
                        dataFixerDto.getDate(),
                        eurToChf,
                        eurToChf.multiply(ratesDto.getPln()).round(MATH_CONTEXT),
                        eurToChf.multiply(ratesDto.getGbp()).round(MATH_CONTEXT),
                        BigDecimal.ONE,
                        eurToChf.multiply(ratesDto.getUsd()).round(MATH_CONTEXT)
                );
            case "USD":
                BigDecimal eurToUsd = BigDecimal.ONE.divide(ratesDto.getUsd(), SCALE, RoundingMode.CEILING);
                return new CurrencyBase(
                        "USD",
                        dataFixerDto.getDate(),
                        eurToUsd,
                        eurToUsd.multiply(ratesDto.getPln()).round(MATH_CONTEXT),
                        eurToUsd.multiply(ratesDto.getGbp()).round(MATH_CONTEXT),
                        eurToUsd.multiply(ratesDto.getChf()).round(MATH_CONTEXT),
                        BigDecimal.ONE
                );
            default:
                return null;
        }
    }

    private DataFixerDto getDataFixerData() {
        return dataFixerClient.getDataFixerData();
    }
}
