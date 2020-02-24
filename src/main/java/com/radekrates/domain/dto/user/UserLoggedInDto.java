package com.radekrates.domain.dto.user;

import com.radekrates.domain.dto.iban.IbanDto;

import com.radekrates.domain.dto.transaction.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserLoggedInDto {
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private Set<IbanDto> ibans;
    private Set<TransactionDto> transactions;
}
