package com.radekrates.service.exceptions.iban;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Provided data not correct")
public class IbanDataConflictException extends RuntimeException {
}
