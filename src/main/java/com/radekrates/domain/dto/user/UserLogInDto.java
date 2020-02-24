package com.radekrates.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLogInDto {
    private Long id;
    private String userEmail;
    private String password;
}
