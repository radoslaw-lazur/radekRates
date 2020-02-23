package com.radekrates.service.exceptions.iban;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Iban has already been saved to user or user not active/blocked")
public class IbanToUserConflictException extends RuntimeException {
}
