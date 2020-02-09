package com.radekrates.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User has not been found in Database")
public class UserNotFoundException extends RuntimeException {
}
