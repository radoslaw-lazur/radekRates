package com.radekrates.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserActivationDto {
    private Long id;
    private String userEmail;
    private String activationCodeFront;
}
