package com.radekrates.service.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Transaction has already been saved to user")
public class TransactionToUserConflictException extends RuntimeException {
}
