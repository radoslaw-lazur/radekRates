package com.radekrates.service.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Provided data not correct")
public class UserDataConflictException extends RuntimeException{
}
