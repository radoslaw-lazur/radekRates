package com.radekrates.domain.dto.iban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IbanDto {
    private Long id;
    private String bankName;
    private String bankLocalisation;
    private String countryCode;
    private String ibanNumber;
}
