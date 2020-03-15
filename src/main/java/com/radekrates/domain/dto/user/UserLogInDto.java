package com.radekrates.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLogInDto {
    private Long id;
    private String userEmail;
    private String password;
}
