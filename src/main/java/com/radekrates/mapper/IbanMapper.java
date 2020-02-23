package com.radekrates.mapper;

import com.radekrates.domain.Iban;
import com.radekrates.domain.dto.iban.IbanDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class IbanMapper {
    public Iban mapToIban(final IbanDto ibanDto) {
        return new Iban(
                ibanDto.getId(),
                ibanDto.getBankName(),
                ibanDto.getBankLocalisation(),
                ibanDto.getCountryCode(),
                ibanDto.getIbanNumber()
        );
    }
    public IbanDto mapToIbanDto(final Iban iban) {
        return new IbanDto(
                iban.getId(),
                iban.getBankName(),
                iban.getBankLocalisation(),
                iban.getCountryCode(),
                iban.getIbanNumber()
        );
    }
    public Set<IbanDto> mapToIbanDtoSet(final Set<Iban> ibans) {
        return ibans.stream()
                .map(i -> new IbanDto(i.getId(), i.getBankName(), i.getBankLocalisation(),
                        i.getCountryCode(), i.getIbanNumber()))
                .collect(Collectors.toSet());
    }
}
