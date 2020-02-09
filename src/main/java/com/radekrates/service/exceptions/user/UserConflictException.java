package com.radekrates.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User has already been saved to Database")
public class UserConflictException extends RuntimeException {
}
