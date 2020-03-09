package com.radekrates.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String userFirstName;
    private String userLastName;
    private int age;
    private String country;
    private String activationCode;
    private boolean isActive;
    private boolean isBlocked;
}
