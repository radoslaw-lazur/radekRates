package com.radekrates.service.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Admin key has not been found in database")
public class AdminKeyNotFoundException extends RuntimeException {
}
