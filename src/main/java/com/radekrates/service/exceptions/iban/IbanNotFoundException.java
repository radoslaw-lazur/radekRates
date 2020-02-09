package com.radekrates.service.exceptions.iban;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Iban has not been found in database")
public class IbanNotFoundException extends RuntimeException {
}
