package com.radekrates.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
}
