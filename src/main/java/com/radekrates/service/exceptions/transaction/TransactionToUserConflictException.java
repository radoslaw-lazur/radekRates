package com.radekrates.service.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Transaction has already been saved to user or user not active/blocked")
public class TransactionToUserConflictException extends RuntimeException {
}
