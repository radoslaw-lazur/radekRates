package com.radekrates.mapper;

import com.radekrates.domain.AdminRatio;
import com.radekrates.domain.dto.adminratio.AdminRatioDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdminRatioMapper {
    public AdminRatio mapToAdminRatio(final AdminRatioDto adminRatioDto) {
        return new AdminRatio(
                adminRatioDto.getId(),
                adminRatioDto.getKey(),
                adminRatioDto.getDate(),
                adminRatioDto.isActive(),
                adminRatioDto.getRatioEUR_PLN(),
                adminRatioDto.getRatioEUR_GBP(),
                adminRatioDto.getRatioEUR_CHF(),
                adminRatioDto.getRatioEUR_USD(),
                adminRatioDto.getRatioPLN_EUR(),
                adminRatioDto.getRatioPLN_GBP(),
                adminRatioDto.getRatioPLN_CHF(),
                adminRatioDto.getRatioPLN_USD(),
                adminRatioDto.getRatioGBP_EUR(),
                adminRatioDto.getRatioGBP_PLN(),
                adminRatioDto.getRatioGBP_CHF(),
                adminRatioDto.getRatioGBP_USD(),
                adminRatioDto.getRatioCHF_EUR(),
                adminRatioDto.getRatioCHF_PLN(),
                adminRatioDto.getRatioCHF_GBP(),
                adminRatioDto.getRatioCHF_USD(),
                adminRatioDto.getRatioUSD_EUR(),
                adminRatioDto.getRatioUSD_PLN(),
                adminRatioDto.getRatioUSD_GBP(),
                adminRatioDto.getRatioUSD_CHF()
        );
    }
    public Set<AdminRatioDto> mapToAdminRatioDtoSet(final Set<AdminRatio> adminRatios) {
        return adminRatios.stream()
                .map(a -> new AdminRatioDto(a.getId(), a.getKey(), a.getDate(), a.isActive(), a.getRatioEUR_PLN(),
                        a.getRatioEUR_GBP(), a.getRatioEUR_CHF(), a.getRatioEUR_USD(), a.getRatioPLN_EUR(),
                        a.getRatioPLN_GBP(), a.getRatioPLN_CHF(), a.getRatioPLN_USD(), a.getRatioGBP_EUR(),
                        a.getRatioGBP_PLN(), a.getRatioGBP_CHF(), a.getRatioGBP_USD(), a.getRatioCHF_EUR(),
                        a.getRatioCHF_PLN(), a.getRatioCHF_GBP(), a.getRatioCHF_USD(), a.getRatioUSD_EUR(),
                        a.getRatioUSD_PLN(), a.getRatioUSD_GBP(), a.getRatioUSD_CHF()))
                .collect(Collectors.toSet());
    }
}
