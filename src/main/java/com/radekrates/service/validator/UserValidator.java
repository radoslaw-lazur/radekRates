package com.radekrates.service.validator;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\." +
            "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean validateUserEmail(String userEmail) {
        return EMAIL_PATTERN.matcher(userEmail).matches();
    }

    public boolean validatePassword(String userPassword) {
        return PASSWORD_PATTERN.matcher(userPassword).matches();
    }
}
