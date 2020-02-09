package com.radekrates.domain.dto.iban;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IbanToUserDto {
    private String userEmail;
    private String iban;
}
