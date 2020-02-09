package com.radekrates.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String eMail;
    private String password;
    private String userFirstName;
    private String userLastName;
    private int age;
    private String country;
    private boolean isActive;
    private boolean isBlocked;
}
